package com.library.management.repository;

import com.library.management.model.Author;
import com.library.management.model.Book;
import com.library.management.model.BookAuthorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BookRepository.
 * Uses @DataJpaTest for an embedded database with auto-configured JPA components.
 */
@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    private Author testAuthor;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test Author", "testauthor@test.com",
                "A test author", "British");
        entityManager.persist(testAuthor);

        testBook = new Book("Test Book", "978-1234567890", "Fiction",
                2023, 19.99, testAuthor);
        entityManager.persist(testBook);
        entityManager.flush();
    }

    @Test
    @DisplayName("Test: Save a new book")
    void testSaveBook() {
        Book newBook = new Book("New Book", "978-0987654321", "Mystery",
                2024, 14.99, testAuthor);
        Book savedBook = bookRepository.save(newBook);

        assertNotNull(savedBook.getId());
        assertEquals("New Book", savedBook.getTitle());
        assertEquals("978-0987654321", savedBook.getIsbn());
    }

    @Test
    @DisplayName("Test: Find book by ID")
    void testFindById() {
        Optional<Book> found = bookRepository.findById(testBook.getId());

        assertTrue(found.isPresent());
        assertEquals("Test Book", found.get().getTitle());
    }

    @Test
    @DisplayName("Test: Find all books")
    void testFindAll() {
        Book anotherBook = new Book("Another Book", "978-1111111111", "Drama",
                2022, 12.50, testAuthor);
        entityManager.persist(anotherBook);
        entityManager.flush();

        List<Book> books = bookRepository.findAll();
        assertTrue(books.size() >= 2);
    }

    @Test
    @DisplayName("Test: Find books by title containing keyword")
    void testFindByTitleContainingIgnoreCase() {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCase("test");

        assertFalse(books.isEmpty());
        assertEquals("Test Book", books.get(0).getTitle());
    }

    @Test
    @DisplayName("Test: Find books by genre")
    void testFindByGenre() {
        List<Book> fictionBooks = bookRepository.findByGenre("Fiction");

        assertFalse(fictionBooks.isEmpty());
        assertEquals("Fiction", fictionBooks.get(0).getGenre());
    }

    @Test
    @DisplayName("Test: Find books by author ID")
    void testFindByAuthorId() {
        List<Book> books = bookRepository.findByAuthorId(testAuthor.getId());

        assertFalse(books.isEmpty());
        assertEquals(testAuthor.getId(), books.get(0).getAuthor().getId());
    }

    @Test
    @DisplayName("Test: Find book by ISBN")
    void testFindByIsbn() {
        Book found = bookRepository.findByIsbn("978-1234567890");

        assertNotNull(found);
        assertEquals("Test Book", found.getTitle());
    }

    @Test
    @DisplayName("Test: Custom JPQL Inner Join - Find all books with authors")
    void testFindAllBooksWithAuthors() {
        List<BookAuthorDTO> results = bookRepository.findAllBooksWithAuthors();

        assertFalse(results.isEmpty());
        BookAuthorDTO dto = results.get(0);
        assertEquals("Test Book", dto.getBookTitle());
        assertEquals("Test Author", dto.getAuthorName());
        assertNotNull(dto.getBookId());
        assertNotNull(dto.getAuthorId());
    }

    @Test
    @DisplayName("Test: Update an existing book")
    void testUpdateBook() {
        testBook.setTitle("Updated Test Book");
        testBook.setPrice(24.99);
        Book updated = bookRepository.save(testBook);

        assertEquals("Updated Test Book", updated.getTitle());
        assertEquals(24.99, updated.getPrice());
    }

    @Test
    @DisplayName("Test: Find book by non-existent ID returns empty")
    void testFindByNonExistentId() {
        Optional<Book> found = bookRepository.findById(999L);
        assertFalse(found.isPresent());
    }
}
