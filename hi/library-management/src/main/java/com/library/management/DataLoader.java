package com.library.management;
 
import com.library.management.model.Author;
import com.library.management.model.Book;
import com.library.management.repository.AuthorRepository;
import com.library.management.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
 
/**
 * Custom data loader that sets up the library with initial records.
 * It pre-registers 10 authors and their famous works for demonstration.
 */
@Component
public class DataLoader implements CommandLineRunner {
 
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
 
    @Autowired
    public DataLoader(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }
 
    @Override
    public void run(String... args) throws Exception {
        if (authorRepository.count() == 0) {
            populateData();
        }
    }
 
    private void populateData() {
        // Sample Authors with unique bios
        Author author1 = new Author("George Orwell", "orwell@library.hub",
                "A British writer famous for his deep insights into political power and social justice.", "British");
        Author author2 = new Author("Jane Austen", "austen@library.hub",
                "Known for capturing the nuances of early 19th-century society with wit and charm.", "British");
        Author author3 = new Author("Mark Twain", "twain@library.hub",
                "A legendary storyteller whose humor and river-life tales shaped modern American writing.", "American");
        Author author4 = new Author("Leo Tolstoy", "tolstoy@library.hub",
                "A Russian giant of literature whose massive novels explore the depths of history and soul.", "Russian");
        Author author5 = new Author("Gabriel Garcia Marquez", "marquez@library.hub",
                "A master of storytelling who brought the world of magical realism to global fame.", "Colombian");
        Author author6 = new Author("Haruki Murakami", "murakami@library.hub",
                "A modern Japanese voice creating dreamy, surreal worlds within urban life.", "Japanese");
        Author author7 = new Author("Agatha Christie", "christie@library.hub",
                "The unparalleled expert of the classic mystery, keeping readers guessing for decades.", "British");
        Author author8 = new Author("Fyodor Dostoevsky", "dostoevsky@library.hub",
                "Explored the darkest corners of human psychology in his powerful Russian narratives.", "Russian");
        Author author9 = new Author("Virginia Woolf", "woolf@library.hub",
                "A pioneer of modernist prose who experimented with how we experience time and thought.", "British");
        Author author10 = new Author("Ernest Hemingway", "hemingway@library.hub",
                "Famous for a direct, powerful writing style that focused on courage and survival.", "American");
 
        authorRepository.save(author1);
        authorRepository.save(author2);
        authorRepository.save(author3);
        authorRepository.save(author4);
        authorRepository.save(author5);
        authorRepository.save(author6);
        authorRepository.save(author7);
        authorRepository.save(author8);
        authorRepository.save(author9);
        authorRepository.save(author10);
 
        // Sample Books
        bookRepository.save(new Book("1984", "ISBN-9780451524935", "Dystopian", 1949, 12.50, author1));
        bookRepository.save(new Book("Pride and Prejudice", "ISBN-9780141439518", "Classic", 1813, 8.99, author2));
        bookRepository.save(new Book("Huckleberry Finn", "ISBN-9780486280615", "Adventure", 1884, 6.75, author3));
        bookRepository.save(new Book("War and Peace", "ISBN-9780199232765", "Historical", 1869, 18.25, author4));
        bookRepository.save(new Book("One Hundred Years of Solitude", "ISBN-9780060883287", "Fantasy", 1967, 13.40, author5));
        bookRepository.save(new Book("Norwegian Wood", "ISBN-9780375704024", "Contemporary", 1987, 10.95, author6));
        bookRepository.save(new Book("Murder on the Orient Express", "ISBN-9780062693662", "Mystery", 1934, 9.50, author7));
        bookRepository.save(new Book("Crime and Punishment", "ISBN-9780486415871", "Psychological", 1866, 7.95, author8));
        bookRepository.save(new Book("Mrs Dalloway", "ISBN-9780156628709", "Modernist", 1925, 11.20, author9));
        bookRepository.save(new Book("The Old Man and the Sea", "ISBN-9780684801223", "Fiction", 1952, 10.80, author10));
 
        System.out.println("System initialized with default author and book registry.");
    }
}
