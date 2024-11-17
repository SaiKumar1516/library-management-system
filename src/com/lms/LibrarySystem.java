package com.lms;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibrarySystem {
	
	//Book Management
	
	public boolean addBook(Book book) {
		String query = "INSERT INTO books (title, author, isbn, genre, quantity) VALUES (?, ?, ?, ?, ?)";
		
		try {
			Connection connection = DatabaseConnection.getConnection();
			PreparedStatement prepareStatement = connection.prepareStatement(query);
			 prepareStatement.setString(1, book.getTitle());
			 prepareStatement.setString(2, book.getAuthor());
			 prepareStatement.setString(3, book.getIsbn());
			 prepareStatement.setString(4, book.getGenre());
			 prepareStatement.setInt(5, book.getQuantity());
			 
			 int update = prepareStatement.executeUpdate();
			 return update > 0;
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return false;
		}
		
		
		
	}
	
	public boolean updateBook(Book book) {
        String query = "UPDATE books SET title = ?, author = ?, isbn = ?, genre = ?, quantity = ? WHERE book_id = ?";
        
        try {
        	Connection connection = DatabaseConnection.getConnection();
             PreparedStatement prepareStatement = connection.prepareStatement(query);
            prepareStatement.setString(1, book.getTitle());
             prepareStatement.setString(2, book.getAuthor());
             prepareStatement.setString(3, book.getIsbn());
             prepareStatement.setString(4, book.getGenre());
             prepareStatement.setInt(5, book.getQuantity());
             prepareStatement.setInt(6, book.getBookId());
             
             int update = prepareStatement.executeUpdate() ;
             
           			return update > 0;
           			
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
	
	 public boolean removeBook(int bookId) {
	        String query = "DELETE FROM books WHERE book_id = ?";
	        try
	        {
	        	Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, bookId);
	            
	            int update = preparedStatement.executeUpdate();
	            
	            return update > 0;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
		//Borrower Management
	 
	 public boolean addBorrower(Borrower borrower) {
			String query = "INSERT INTO borrowers (name, contact_details) VALUES (?, ?)";
			
			try {
				Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);
				
				preparedStatement.setString(1,borrower.getName());
				preparedStatement.setString(2, borrower.getContactDetails());
				
				 int update = preparedStatement.executeUpdate();
				 return update > 0;
				 
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				 return false;
			}
			
			
		}
	 
	 public boolean updateBorrower(Borrower borrower) {
	        String query = "UPDATE borrowers SET name = ?, contact_details = ?, WHERE membership_id = ?";
	        
	        try {
	        	Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             
	             preparedStatement.setString(1,borrower.getName());
					preparedStatement.setString(2, borrower.getContactDetails());
					
	             int update = preparedStatement.executeUpdate() ;
	             
	           			return update > 0;
	           			
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 public boolean removeBorrower(int membershipId) {
	        String query = "DELETE FROM borrowers WHERE membership_id = ?";
	        try
	        {
	        	Connection connection = DatabaseConnection.getConnection();
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	            preparedStatement.setInt(1, membershipId);
	            
	            int update = preparedStatement.executeUpdate();
	            
	            return update > 0;
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	 
	 //Book Borrowing and Returning
	 
	 public boolean borrowBook(int bookId, int membershipId, Date dueDate) {
	        String borrowQuery = "INSERT INTO transactions (book_id, membership_id, borrow_date, due_date) VALUES (?, ?, NOW(), ?)";
	        String updateBookQuery = "UPDATE books SET quantity = quantity - 1 WHERE book_id = ?";
	        Connection connection=null;
	        try {
	        	 connection = DatabaseConnection.getConnection();
	        		
	             PreparedStatement borrowStmt = connection.prepareStatement(borrowQuery);
	        		
	             PreparedStatement updateBookStmt = connection.prepareStatement(updateBookQuery);

	            connection.setAutoCommit(false);  // Start transaction

	            borrowStmt.setInt(1, bookId);
	            borrowStmt.setInt(2, membershipId);
	            borrowStmt.setDate(3, dueDate);
	            borrowStmt.executeUpdate();

	            updateBookStmt.setInt(1, bookId);
	            updateBookStmt.executeUpdate();

	            connection.commit();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            if(connection != null) {
	            try {
	                connection.rollback();  // Rollback in case of error
	            } catch (SQLException rollbackEx) {
	                rollbackEx.printStackTrace();
	            }
	         
	        }
	            return false;
	    }
	 }
	 
	 
	 public boolean returnBook(int transactionId) {
	        String returnQuery = "UPDATE transactions SET return_date = NOW() WHERE transaction_id = ?";
	        String updateBookQuery = "UPDATE books SET quantity = quantity + 1 WHERE book_id = (SELECT book_id FROM transactions WHERE transaction_id = ?)";
	        
	        Connection connection=null;
	        try {
	        		connection = DatabaseConnection.getConnection();
	             PreparedStatement returnStmt = connection.prepareStatement(returnQuery);
	             PreparedStatement updateBookStmt = connection.prepareStatement(updateBookQuery);

	            connection.setAutoCommit(false);  // Start transaction

	            returnStmt.setInt(1, transactionId);
	            returnStmt.executeUpdate();

	            updateBookStmt.setInt(1, transactionId);
	            updateBookStmt.executeUpdate();

	            connection.commit();
	            return true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	            if(connection != null) {
		            try {
		                connection.rollback();  // Rollback in case of error
		            } catch (SQLException rollbackEx) {
		                rollbackEx.printStackTrace();
		            }
	            }
	            	return false;
	        }
	 }	 
	 
	 //Book search and show Availability in results
	 
	 public List<Book> searchBooks(String searchKeyword) {
	        String query = "SELECT * FROM books WHERE title LIKE ? OR author LIKE ? OR genre LIKE ?";
	        List<Book> books = new ArrayList<>();
	        try (Connection conn = DatabaseConnection.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(query)) {
	            stmt.setString(1, "%" + searchKeyword + "%");
	            stmt.setString(2, "%" + searchKeyword + "%");
	            stmt.setString(3, "%" + searchKeyword + "%");
	            ResultSet rs = stmt.executeQuery();
	            while (rs.next()) {
	                books.add(new Book(
	                        rs.getInt("book_id"),
	                        rs.getString("title"),
	                        rs.getString("author"),
	                        rs.getString("isbn"),
	                        rs.getString("genre"),
	                        rs.getInt("quantity")
	                ));
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return books;
	    }

}
