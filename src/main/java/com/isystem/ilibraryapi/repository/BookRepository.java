package com.isystem.ilibraryapi.repository;

import com.isystem.ilibraryapi.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);

    Optional<Book> findByAuthor(String author);

    Optional<Book> findByTitleAndAuthorAndGenre(String title, String author, String genre);

}
