package com.library.management.repository;

import com.library.management.model.Book;
import com.library.management.model.BookAuthorDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Book entity.
 * Extends JpaRepository to provide standard CRUD operations.
 * Includes custom query methods using JPQL.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * Find books by title containing a keyword (case-insensitive).
     */
    List<Book> findByTitleContainingIgnoreCase(String title);

    /**
     * Find books by genre.
     */
    List<Book> findByGenre(String genre);

    /**
     * Find books by author ID.
     */
    List<Book> findByAuthorId(Long authorId);

    /**
     * Custom query: Inner join between Book and Author entities.
     * Returns a list of BookAuthorDTO containing combined data from both entities.
     */
    @Query("SELECT new com.library.management.model.BookAuthorDTO(" +
           "b.id, b.title, b.isbn, b.genre, b.publishedYear, b.price, " +
           "a.id, a.name, a.email, a.nationality) " +
           "FROM Book b INNER JOIN b.author a")
    List<BookAuthorDTO> findAllBooksWithAuthors();

    /**
     * Find book by ISBN.
     */
    Book findByIsbn(String isbn);
}
