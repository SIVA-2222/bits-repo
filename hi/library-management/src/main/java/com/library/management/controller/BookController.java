package com.library.management.controller;

import com.library.management.model.Author;
import com.library.management.model.Book;
import com.library.management.model.BookAuthorDTO;
import com.library.management.service.AuthorService;
import com.library.management.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * Controller to handle book management workflows.
 */
@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    /**
     * Display list of all books.
     * READ operation - fetches data from service layer and binds to JSP view.
     */
    @GetMapping
    public String listBooks(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book-list";
    }

    /**
     * Display inner join results - Books with their Authors.
     * READ operation - uses custom query method in repository layer.
     */
    @GetMapping("/with-authors")
    public String listBooksWithAuthors(Model model) {
        List<BookAuthorDTO> booksWithAuthors = bookService.getAllBooksWithAuthors();
        model.addAttribute("booksWithAuthors", booksWithAuthors);
        return "book-author-list";
    }

    /**
     * Show the form to add a new book.
     * CREATE operation - displays JSP form with author dropdown.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book-form";
    }

    /**
     * Handle form submission to save a new book.
     * CREATE operation - saves entity and handles integrity violations.
     */
    @PostMapping("/save")
    public String saveBook(@Valid @ModelAttribute("book") Book book,
                           BindingResult result,
                           @RequestParam("authorId") Long authorId,
                           Model model,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book-form";
        }
        try {
            Author author = authorService.getAuthorById(authorId);
            if (author == null) {
                model.addAttribute("errorMessage", "Error: Selected author not found.");
                model.addAttribute("authors", authorService.getAllAuthors());
                return "book-form";
            }
            book.setAuthor(author);
            bookService.saveBook(book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "Great! '" + book.getTitle() + "' is now part of the catalog.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "That ISBN is already in our records. Please verify the code.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book-form";
        }
        return "redirect:/books";
    }

    /**
     * Show the form to edit an existing book.
     * UPDATE operation - pre-populates form with existing data.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Book book = bookService.getBookById(id);
        if (book == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Book not found with ID: " + id);
            return "redirect:/books";
        }
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthors());
        return "book-form";
    }

    /**
     * Handle form submission to update an existing book.
     * UPDATE operation - saves updated entity and handles integrity violations.
     */
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id,
                             @Valid @ModelAttribute("book") Book book,
                             BindingResult result,
                             @RequestParam("authorId") Long authorId,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book-form";
        }
        try {
            Author author = authorService.getAuthorById(authorId);
            if (author == null) {
                model.addAttribute("errorMessage", "Error: Selected author not found.");
                model.addAttribute("authors", authorService.getAllAuthors());
                return "book-form";
            }
            book.setAuthor(author);
            bookService.updateBook(id, book);
            redirectAttributes.addFlashAttribute("successMessage",
                    "The book details for '" + book.getTitle() + "' have been updated.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "A duplicate ISBN was detected. Please provide a unique one.");
            model.addAttribute("authors", authorService.getAllAuthors());
            return "book-form";
        }
        return "redirect:/books";
    }
}
