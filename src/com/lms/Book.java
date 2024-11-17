package com.lms;

public class Book {
	 private int bookId;
	    private String title;
	    private String author;
	    private String isbn;
	    private String genre;
	    private int quantity;
	    
	    
		public Book(int bookId, String title, String author, String isbn, String genre, int quantity) {
			
			this.bookId = bookId;
			this.title = title;
			this.author = author;
			this.isbn = isbn;
			this.genre = genre;
			this.quantity = quantity;
		}


		public int getBookId() {
			return bookId;
		}


		public void setBookId(int bookId) {
			this.bookId = bookId;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getAuthor() {
			return author;
		}


		public void setAuthor(String author) {
			this.author = author;
		}


		public String getIsbn() {
			return isbn;
		}


		public void setIsbn(String isbn) {
			this.isbn = isbn;
		}


		public String getGenre() {
			return genre;
		}


		public void setGenre(String genre) {
			this.genre = genre;
		}


		public int getQuantity() {
			return quantity;
		}


		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}
		
		
		
	    
	    

}
