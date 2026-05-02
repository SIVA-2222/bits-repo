package com.library.management.service;

import com.library.management.model.Author;
import com.library.management.model.Book;
import com.library.management.model.BookAuthorDTO;
import com.library.management.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for BookService using Mockito.
 * Mocks the BookRepository to test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
public class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    private Author testAuthor;
    private Book testBook;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("Test Author", "testauthor@test.com",
                "A test author", "British");
        testAuthor.setId(1L);

        testBook = new Book("Test Book", "978-1234567890", "Fiction",
                2023, 19.99, testAuthor);
        testBook.setId(1L);
    }

    @Test
    @DisplayName("Test: Get all books")
    void testGetAllBooks() {
        Book book2 = new Book("Another Book", "978-0987654321", "Mystery",
                2024, 14.99, testAuthor);
        book2.setId(2L);

        when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook, book2));

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test: Get book by ID - found")
    void testGetBookById_Found() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test: Get book by ID - not found")
    void testGetBookById_NotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        Book result = bookService.getBookById(999L);

        assertNull(result);
        verify(bookRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Test: Save a new book")
    void testSaveBook() {
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.saveBook(testBook);

        assertNotNull(result);
        assertEquals("Test Book", result.getTitle());
        assertEquals("978-1234567890", result.getIsbn());
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    @DisplayName("Test: Update an existing book")
    void testUpdateBook_Success() {
        Book updatedDetails = new Book("Updated Book", "978-1111111111", "Drama",
                2024, 24.99, testAuthor);

        when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
        when(bookRepository.save(any(Book.class))).thenReturn(testBook);

        Book result = bookService.updateBook(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("Updated Book", testBook.getTitle());
        assertEquals(24.99, testBook.getPrice());
        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(testBook);
    }

    @Test
    @DisplayName("Test: Update book - not found")
    void testUpdateBook_NotFound() {
        when(bookRepository.findById(999L)).thenReturn(Optional.empty());

        Book result = bookService.updateBook(999L, testBook);

        assertNull(result);
        verify(bookRepository, times(1)).findById(999L);
        verify(bookRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test: Get all books with authors (Inner Join)")
    void testGetAllBooksWithAuthors() {
        BookAuthorDTO dto = new BookAuthorDTO(1L, "Test Book", "978-1234567890",
                "Fiction", 2023, 19.99, 1L, "Test Author", "testauthor@test.com", "British");

        when(bookRepository.findAllBooksWithAuthors()).thenReturn(List.of(dto));

        List<BookAuthorDTO> results = bookService.getAllBooksWithAuthors();

        assertEquals(1, results.size());
        assertEquals("Test Book", results.get(0).getBookTitle());
        assertEquals("Test Author", results.get(0).getAuthorName());
        verify(bookRepository, times(1)).findAllBooksWithAuthors();
    }

    @Test
    @DisplayName("Test: Search books by title")
    void testSearchBooksByTitle() {
        when(bookRepository.findByTitleContainingIgnoreCase("test"))
                .thenReturn(List.of(testBook));

        List<Book> results = bookService.searchBooksByTitle("test");

        assertEquals(1, results.size());
        assertEquals("Test Book", results.get(0).getTitle());
        verify(bookRepository, times(1)).findByTitleContainingIgnoreCase("test");
    }

    @Test
    @DisplayName("Test: Get books by genre")
    void testGetBooksByGenre() {
        when(bookRepository.findByGenre("Fiction")).thenReturn(List.of(testBook));

        List<Book> results = bookService.getBooksByGenre("Fiction");

        assertEquals(1, results.size());
        assertEquals("Fiction", results.get(0).getGenre());
        verify(bookRepository, times(1)).findByGenre("Fiction");
    }

    @Test
    @DisplayName("Test: Get books by author ID")
    void testGetBooksByAuthorId() {
        when(bookRepository.findByAuthorId(1L)).thenReturn(List.of(testBook));

        List<Book> results = bookService.getBooksByAuthorId(1L);

        assertEquals(1, results.size());
        assertEquals(testAuthor.getId(), results.get(0).getAuthor().getId());
        verify(bookRepository, times(1)).findByAuthorId(1L);
    }
}
