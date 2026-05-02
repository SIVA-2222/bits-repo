package com.library.management.model;

/**
 * A data transfer object used to merge details from both the book
 * and author tables for custom reports.
 */
public class BookAuthorDTO {

    private Long bookId;
    private String bookTitle;
    private String isbn;
    private String genre;
    private int publishedYear;
    private double price;
    private Long authorId;
    private String authorName;
    private String authorEmail;
    private String authorNationality;

    // Default constructor
    public BookAuthorDTO() {
    }

    // Parameterized constructor
    public BookAuthorDTO(Long bookId, String bookTitle, String isbn, String genre,
                         int publishedYear, double price, Long authorId,
                         String authorName, String authorEmail, String authorNationality) {
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.isbn = isbn;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.price = price;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorNationality = authorNationality;
    }

    // Getters and Setters
    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
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

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorNationality() {
        return authorNationality;
    }

    public void setAuthorNationality(String authorNationality) {
        this.authorNationality = authorNationality;
    }
}
