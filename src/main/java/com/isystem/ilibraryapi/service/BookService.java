package com.isystem.ilibraryapi.service;

import com.isystem.ilibraryapi.exceptions.BookDuplicateException;
import com.isystem.ilibraryapi.model.Book;
import com.isystem.ilibraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        Optional<Book> book = bookRepository.findById(id);
        if (book.isPresent()) {
            return book.get();
        }
        throw new IllegalArgumentException("Book not found with id " + id);
    }

    public Book getBookByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        if (book.isPresent()) {
            return book.get();
        }
        throw new IllegalArgumentException("Book not found with title " + title);
    }

    public Book getBookByAuthor(String author) {
        Optional<Book> optional = bookRepository.findByAuthor(author);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("Author not found with name " + author);
    }

    public Book register(Book book) {
        return bookRepository.save(book);
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

}
