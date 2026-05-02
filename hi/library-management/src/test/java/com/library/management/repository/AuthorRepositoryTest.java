package com.library.management.repository;

import com.library.management.model.Author;
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
 * Unit tests for AuthorRepository.
 * Uses @DataJpaTest for an embedded database with auto-configured JPA components.
 */
@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private AuthorRepository authorRepository;

    private Author testAuthor;

    @BeforeEach
    void setUp() {
        testAuthor = new Author("John Doe", "john.doe@test.com",
                "A prolific test author", "American");
        entityManager.persist(testAuthor);
        entityManager.flush();
    }

    @Test
    @DisplayName("Test: Save a new author")
    void testSaveAuthor() {
        Author newAuthor = new Author("Jane Smith", "jane.smith@test.com",
                "Another test author", "British");
        Author savedAuthor = authorRepository.save(newAuthor);

        assertNotNull(savedAuthor.getId());
        assertEquals("Jane Smith", savedAuthor.getName());
        assertEquals("jane.smith@test.com", savedAuthor.getEmail());
    }

    @Test
    @DisplayName("Test: Find author by ID")
    void testFindById() {
        Optional<Author> found = authorRepository.findById(testAuthor.getId());

        assertTrue(found.isPresent());
        assertEquals("John Doe", found.get().getName());
    }

    @Test
    @DisplayName("Test: Find all authors")
    void testFindAll() {
        Author anotherAuthor = new Author("Alice Johnson", "alice@test.com",
                "Yet another author", "Canadian");
        entityManager.persist(anotherAuthor);
        entityManager.flush();

        List<Author> authors = authorRepository.findAll();
        assertTrue(authors.size() >= 2);
    }

    @Test
    @DisplayName("Test: Find authors by name containing keyword")
    void testFindByNameContainingIgnoreCase() {
        List<Author> authors = authorRepository.findByNameContainingIgnoreCase("john");

        assertFalse(authors.isEmpty());
        assertEquals("John Doe", authors.get(0).getName());
    }

    @Test
    @DisplayName("Test: Find author by email")
    void testFindByEmail() {
        Author found = authorRepository.findByEmail("john.doe@test.com");

        assertNotNull(found);
        assertEquals("John Doe", found.getName());
    }

    @Test
    @DisplayName("Test: Find authors by nationality")
    void testFindByNationality() {
        List<Author> americans = authorRepository.findByNationality("American");

        assertFalse(americans.isEmpty());
        assertEquals("American", americans.get(0).getNationality());
    }

    @Test
    @DisplayName("Test: Update an existing author")
    void testUpdateAuthor() {
        testAuthor.setName("John Updated");
        testAuthor.setBiography("Updated biography");
        Author updated = authorRepository.save(testAuthor);

        assertEquals("John Updated", updated.getName());
        assertEquals("Updated biography", updated.getBiography());
    }

    @Test
    @DisplayName("Test: Find author by non-existent ID returns empty")
    void testFindByNonExistentId() {
        Optional<Author> found = authorRepository.findById(999L);
        assertFalse(found.isPresent());
    }
}
