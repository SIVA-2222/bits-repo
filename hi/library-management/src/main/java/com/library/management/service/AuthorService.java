package com.library.management.service;

import com.library.management.model.Author;
import com.library.management.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing Author entities.
 * Handles business logic and integrates with the AuthorRepository.
 */
@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Retrieve all authors from the database.
     */
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    /**
     * Find an author by their ID.
     */
    public Author getAuthorById(Long id) {
        Optional<Author> author = authorRepository.findById(id);
        return author.orElse(null);
    }

    /**
     * Save a new author to the database.
     * Throws DataIntegrityViolationException if email already exists.
     */
    public Author saveAuthor(Author author) throws DataIntegrityViolationException {
        return authorRepository.save(author);
    }

    /**
     * Update an existing author's details.
     */
    public Author updateAuthor(Long id, Author authorDetails) throws DataIntegrityViolationException {
        Author existingAuthor = getAuthorById(id);
        if (existingAuthor != null) {
            existingAuthor.setName(authorDetails.getName());
            existingAuthor.setEmail(authorDetails.getEmail());
            existingAuthor.setBiography(authorDetails.getBiography());
            existingAuthor.setNationality(authorDetails.getNationality());
            return authorRepository.save(existingAuthor);
        }
        return null;
    }

    /**
     * Search authors by name keyword.
     */
    public List<Author> searchAuthorsByName(String name) {
        return authorRepository.findByNameContainingIgnoreCase(name);
    }

    /**
     * Find authors by nationality.
     */
    public List<Author> getAuthorsByNationality(String nationality) {
        return authorRepository.findByNationality(nationality);
    }
}
