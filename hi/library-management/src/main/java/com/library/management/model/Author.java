package com.library.management.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

/**
 * Model class for Author records.
 */
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Please provide the author's name")
    @Size(min = 2, max = 100, message = "The name should be between 2 and 100 characters")
    @Column(nullable = false)
    private String name;

    @Size(max = 100, message = "Email must be less than 100 characters")
    @Column(unique = true)
    private String email;

    @Size(max = 500, message = "Biography must be less than 500 characters")
    @Column(length = 500)
    private String biography;

    @Size(max = 50, message = "Nationality must be less than 50 characters")
    private String nationality;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();

    // Default constructor
    public Author() {
    }

    // Parameterized constructor
    public Author(String name, String email, String biography, String nationality) {
        this.name = name;
        this.email = email;
        this.biography = biography;
        this.nationality = nationality;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
