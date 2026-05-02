package com.library.management.service;

import com.library.management.model.Book;
import com.library.management.model.BookAuthorDTO;
import com.library.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Book entities.
 * Handles business logic and integrates with the BookRepository.
 */
@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Retrieve all books from the database.
     */
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    /**
     * Find a book by its ID.
     */
    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    /**
     * Save a new book to the database.
     * Throws DataIntegrityViolationException if ISBN already exists.
     */
    public Book saveBook(Book book) throws DataIntegrityViolationException {
        return bookRepository.save(book);
    }

    /**
     * Update an existing book's details.
     */
    public Book updateBook(Long id, Book bookDetails) throws DataIntegrityViolationException {
        Book existingBook = getBookById(id);
        if (existingBook != null) {
            existingBook.setTitle(bookDetails.getTitle());
            existingBook.setIsbn(bookDetails.getIsbn());
            existingBook.setGenre(bookDetails.getGenre());
            existingBook.setPublishedYear(bookDetails.getPublishedYear());
            existingBook.setPrice(bookDetails.getPrice());
            existingBook.setAuthor(bookDetails.getAuthor());
            return bookRepository.save(existingBook);
        }
        return null;
    }

    /**
     * Custom query: Get all books with their authors using inner join.
     */
    public List<BookAuthorDTO> getAllBooksWithAuthors() {
        return bookRepository.findAllBooksWithAuthors();
    }

    /**
     * Search books by title keyword.
     */
    public List<Book> searchBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    /**
     * Find books by genre.
     */
    public List<Book> getBooksByGenre(String genre) {
        return bookRepository.findByGenre(genre);
    }

    /**
     * Find books by author ID.
     */
    public List<Book> getBooksByAuthorId(Long authorId) {
        return bookRepository.findByAuthorId(authorId);
    }
}
