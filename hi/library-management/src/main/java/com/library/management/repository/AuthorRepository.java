package com.library.management.repository;

import com.library.management.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Author entity.
 * Extends JpaRepository to provide standard CRUD operations.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    /**
     * Find authors by name containing a keyword (case-insensitive).
     */
    List<Author> findByNameContainingIgnoreCase(String name);

    /**
     * Find author by email.
     */
    Author findByEmail(String email);

    /**
     * Find authors by nationality.
     */
    List<Author> findByNationality(String nationality);
}
