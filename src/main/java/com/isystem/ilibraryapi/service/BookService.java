package com.isystem.ilibraryapi.service;

import com.isystem.ilibraryapi.exceptions.BookDuplicateException;
import com.isystem.ilibraryapi.model.Book;
import com.isystem.ilibraryapi.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public Book getBookByAuthor(String author) {
        Optional<Book> optional = bookRepository.findByAuthor(author);
        if (optional.isPresent()) {
            return optional.get();
        }
        throw new IllegalArgumentException("Author not found with name " + author);
    }

    public Book register(Book book) {
        Optional<Book> optional = bookRepository.findByTitleAndAuthorAndGenre(book.getTitle(), book.getAuthor(), book.getGenre());
        if (optional.isEmpty()) {
            return bookRepository.save(book);
        }
        throw new BookDuplicateException("There is already a book with the same title, author and genre information");
    }

    public Book update(Book book) {
        return bookRepository.save(book);
    }

    public void remove(Long id) {
        bookRepository.deleteById(id);
    }

}
