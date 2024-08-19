package com.isystem.ilibraryapi.controller;

import com.isystem.ilibraryapi.exceptions.BookDuplicateException;
import com.isystem.ilibraryapi.model.Book;
import com.isystem.ilibraryapi.repository.BookRepository;
import com.isystem.ilibraryapi.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;
    private final BookRepository bookRepository;

    @Autowired
    public BookController(BookService bookService, BookRepository bookRepository) {
        this.bookService = bookService;
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllUsers() {
        return bookService.getAllBooks();
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createUser(@RequestBody Book book) {
        try {
            return ResponseEntity.ok(bookService.register(book));
        } catch (BookDuplicateException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(e.getMessage());
        }
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam("title") String title) {
        List<Book> books = bookService.getBookByTitle(title);
        if (books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getBookId(@PathVariable Long id) {
        try {
            Book book = bookService.getBookById(id);
            return ResponseEntity.ok(book);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/update")
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        Book existingBook = bookService.getBookById(id);
        try {
            updateIfNotNull(existingBook::setTitle, bookDetails.getTitle());
            updateIfNotNull(existingBook::setAuthor, bookDetails.getAuthor());
            updateIfNotNull(existingBook::setGenre, bookDetails.getGenre());
            updateIfNotNull(existingBook::setPublicationDate, bookDetails.getPublicationDate());
            updateIfNotNull(existingBook::setTotalCopies, bookDetails.getTotalCopies());
            updateIfNotNull(existingBook::setAvailableCopies, bookDetails.getAvailableCopies());
            return ResponseEntity.ok(bookRepository.save(existingBook));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<Book> deleteBook(@PathVariable Long id) {
        bookService.remove(id);
        return ResponseEntity.noContent().build();
    }

    protected <T> void updateIfNotNull(Consumer<T> setter, T value) {
        if (value != null) {
            setter.accept(value);
        }
    }
}
