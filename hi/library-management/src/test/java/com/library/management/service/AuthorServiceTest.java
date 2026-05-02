package com.library.management.service;

import com.library.management.model.Author;
import com.library.management.repository.AuthorRepository;
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
 * Unit tests for AuthorService using Mockito.
 * Mocks the AuthorRepository to test business logic in isolation.
 */
@ExtendWith(MockitoExtension.class)
public class AuthorServiceTest {

    @Mock
    private AuthorRepository authorRepository;

    @InjectMocks
    private AuthorService authorService;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("John Doe", "john.doe@test.com",
                "A prolific test author", "American");
        testAuthor.setId(1L);
    }

    @Test
    @DisplayName("Test: Get all authors")
    void testGetAllAuthors() {
        Author author2 = new Author("Jane Smith", "jane@test.com", "Bio", "British");
        author2.setId(2L);

        when(authorRepository.findAll()).thenReturn(Arrays.asList(testAuthor, author2));

        List<Author> result = authorService.getAllAuthors();

        assertEquals(2, result.size());
        verify(authorRepository, times(1)).findAll();
    }

    @Test
    @DisplayName("Test: Get author by ID - found")
    void testGetAuthorById_Found() {
        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

        Author result = authorService.getAuthorById(1L);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(authorRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Test: Get author by ID - not found")
    void testGetAuthorById_NotFound() {
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());

        Author result = authorService.getAuthorById(999L);

        assertNull(result);
        verify(authorRepository, times(1)).findById(999L);
    }

    @Test
    @DisplayName("Test: Save a new author")
    void testSaveAuthor() {
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        Author result = authorService.saveAuthor(testAuthor);

        assertNotNull(result);
        assertEquals("John Doe", result.getName());
        verify(authorRepository, times(1)).save(testAuthor);
    }

    @Test
    @DisplayName("Test: Update an existing author")
    void testUpdateAuthor_Success() {
        Author updatedDetails = new Author("John Updated", "john.updated@test.com",
                "Updated bio", "Canadian");

        when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
        when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

        Author result = authorService.updateAuthor(1L, updatedDetails);

        assertNotNull(result);
        assertEquals("John Updated", testAuthor.getName());
        assertEquals("john.updated@test.com", testAuthor.getEmail());
        verify(authorRepository, times(1)).findById(1L);
        verify(authorRepository, times(1)).save(testAuthor);
    }

    @Test
    @DisplayName("Test: Update author - not found")
    void testUpdateAuthor_NotFound() {
        when(authorRepository.findById(999L)).thenReturn(Optional.empty());

        Author result = authorService.updateAuthor(999L, testAuthor);

        assertNull(result);
        verify(authorRepository, times(1)).findById(999L);
        verify(authorRepository, never()).save(any());
    }

    @Test
    @DisplayName("Test: Search authors by name")
    void testSearchAuthorsByName() {
        when(authorRepository.findByNameContainingIgnoreCase("john"))
                .thenReturn(List.of(testAuthor));

        List<Author> results = authorService.searchAuthorsByName("john");

        assertEquals(1, results.size());
        assertEquals("John Doe", results.get(0).getName());
        verify(authorRepository, times(1)).findByNameContainingIgnoreCase("john");
    }

    @Test
    @DisplayName("Test: Get authors by nationality")
    void testGetAuthorsByNationality() {
        when(authorRepository.findByNationality("American"))
                .thenReturn(List.of(testAuthor));

        List<Author> results = authorService.getAuthorsByNationality("American");

        assertEquals(1, results.size());
        assertEquals("American", results.get(0).getNationality());
        verify(authorRepository, times(1)).findByNationality("American");
    }
}
