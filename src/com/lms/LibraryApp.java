package com.lms;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class LibraryApp {
	 public static void main(String[] args){
	        LibrarySystem librarySystem = new LibrarySystem();
	        
	        
	        //creating book objects
	        Book book1 = new Book(1, "Java Programming", "James Gosling", "1234567890", "Programming", 5);
	        Book book2=new Book(2, "Sql RelationalModel" , "EF Codd", "sql-1", "Database", 4);
	        Book book3=new Book(3, "html" , "tim berners", "html5", "front-End", 3);
	        
	        //adding the books to database
	        librarySystem.addBook(book1);
	        librarySystem.addBook(book2);
	        librarySystem.addBook(book3);
	        
	        //searching books
	        List<Book> books = librarySystem.searchBooks("Java");
	        for (Book b : books) {
	            System.out.println(b.getTitle() + " by " + b.getAuthor());
	        }
	        
	        //creating borrower objects 
	      Borrower borrower1=new Borrower(1,"saikumar", "9876543210");
	       Borrower borrower2=new Borrower(2,"krishna", "9823543210");
	        
	       librarySystem.addBorrower(borrower1);
	       librarySystem.addBorrower(borrower2);
	        
	        librarySystem.borrowBook(book1.getBookId(),borrower1.getMembershipId() , Date.valueOf("2024-11-20"));
	        librarySystem.borrowBook(book2.getBookId(),borrower2.getMembershipId() , Date.valueOf("2024-11-20"));
	        
	        librarySystem.returnBook(3);
	        librarySystem.returnBook(4);
	        
	        

	    }
	}
