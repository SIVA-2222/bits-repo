package com.library.management.controller;

import com.library.management.model.Author;
import com.library.management.service.AuthorService;
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
 * Web controller for author-related operations.
 */
@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    /**
     * Display list of all authors.
     * READ operation - fetches data from service layer and binds to JSP view.
     */
    @GetMapping
    public String listAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "author-list";
    }

    /**
     * Show the form to add a new author.
     * CREATE operation - displays JSP form.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("author", new Author());
        return "author-form";
    }

    /**
     * Web controller for managing book records.
     */
    @PostMapping("/save")
    public String saveAuthor(@Valid @ModelAttribute("author") Author author,
                             BindingResult result,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "author-form";
        }
        try {
            authorService.saveAuthor(author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "We've successfully added '" + author.getName() + "' to the registry.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "The email provided is already registered. Please use another one.");
            return "author-form";
        }
        return "redirect:/authors";
    }

    /**
     * Show the form to edit an existing author.
     * UPDATE operation - pre-populates form with existing data.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model,
                               RedirectAttributes redirectAttributes) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "Author not found with ID: " + id);
            return "redirect:/authors";
        }
        model.addAttribute("author", author);
        return "author-form";
    }

    /**
     * Handle form submission to update an existing author.
     * UPDATE operation - saves updated entity and handles integrity violations.
     */
    @PostMapping("/update/{id}")
    public String updateAuthor(@PathVariable Long id,
                               @Valid @ModelAttribute("author") Author author,
                               BindingResult result,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "author-form";
        }
        try {
            authorService.updateAuthor(id, author);
            redirectAttributes.addFlashAttribute("successMessage",
                    "The details for '" + author.getName() + "' have been updated.");
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage",
                    "The email provided is already registered. Please use another one.");
            return "author-form";
        }
        return "redirect:/authors";
    }
}
