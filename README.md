# library-management-system
A simplified Library Management System using Java and JDBC.
## Features
- Add, update, and remove books
- Add, update, and remove borrowers
- Borrow and return books
- Search books by title, author, or genre
- Handle due dates

## Technologies Used
- Java
- MySQL
- JDBC

## Setup
1. Clone the repository.
2. Set up a MySQL database with the given schema.
3. Update `DatabaseConnection.java` with your database credentials.
4. Run `LibraryApp.java` to interact with the system.

## Database Schema
---SQL-----
-- Create the books table
CREATE TABLE books (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(30) NOT NULL,
    author VARCHAR(30) NOT NULL,
    isbn VARCHAR(15) NOT NULL,
    genre VARCHAR(50),
    quantity INT NOT NULL
);

--Create the borrowers table
CREATE TABLE borrowers (
    membership_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    contact_details VARCHAR(50)
);

--Create the transactions table
CREATE TABLE transactions (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    book_id INT,
    membership_id INT,
    borrow_date DATE,
    due_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES books(book_id),
    FOREIGN KEY (membership_id) REFERENCES borrowers(membership_id)
);

