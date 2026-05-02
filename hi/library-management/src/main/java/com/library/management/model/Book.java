package com.library.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

/**
 * Data model for Book entities.
 */
@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please enter the book's title")
    @Size(min = 1, max = 200, message = "The title should be under 200 characters")
    @Column(nullable = false)
    private String title;

    @NotBlank(message = "ISBN is required")
    @Column(unique = true, nullable = false)
    private String isbn;

    @Size(max = 50, message = "Genre must be less than 50 characters")
    private String genre;

    @Min(value = 1000, message = "Published year must be a valid year")
    @Max(value = 2100, message = "Published year must be a valid year")
    private int publishedYear;

    @DecimalMin(value = "0.0", message = "Price must be positive")
    private double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    // Default constructor
    public Book() {
    }

    // Parameterized constructor
    public Book(String title, String isbn, String genre, int publishedYear, double price, Author author) {
        this.title = title;
        this.isbn = isbn;
        this.genre = genre;
        this.publishedYear = publishedYear;
        this.price = price;
        this.author = author;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", genre='" + genre + '\'' +
                ", publishedYear=" + publishedYear +
                ", price=" + price +
                '}';
    }
}
