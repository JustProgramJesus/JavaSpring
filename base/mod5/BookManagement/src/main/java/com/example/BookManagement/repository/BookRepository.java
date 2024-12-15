package com.example.BookManagement.repository;

import com.example.BookManagement.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Метод для поиска по имени категории
    List<Book> findByCategory_NameIgnoreCase(String name);

    // Метод для поиска по названию и автору
    List<Book> findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(String title, String author);
}