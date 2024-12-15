package com.example.BookManagement.controller;

import com.example.BookManagement.model.Book;
import com.example.BookManagement.model.Category;
import com.example.BookManagement.repository.BookRepository;
import com.example.BookManagement.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    // GET: Получение всех книг
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // GET: Поиск книги по названию и автору
    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam(required = false) String title,
                                  @RequestParam(required = false) String author,
                                  @RequestParam(required = false) String category) {
        if (category != null && !category.isEmpty()) {
            // Поиск по категории
            return bookRepository.findByCategory_NameIgnoreCase(category);
        } else if (title != null && !title.isEmpty() && author != null && !author.isEmpty()) {
            // Поиск по title и author
            return bookRepository.findByTitleContainingIgnoreCaseAndAuthorContainingIgnoreCase(title, author);
        }
        // Возвращаем все книги, если нет фильтров
        return bookRepository.findAll();
    }

    // GET: Получение книги по ID
    @GetMapping("/{id}")
    public Optional<Book> getBookById(@PathVariable Long id) {
        return bookRepository.findById(id);
    }

    // POST: Создание новой книги
    @PostMapping
    public Book createBook(@RequestBody Book book) {
        // Проверяем, существует ли категория
        Category category = categoryRepository.findByName(book.getCategory().getName())
                .orElseGet(() -> {
                    // Если категории нет, создаем новую
                    return categoryRepository.save(book.getCategory());
                });

        book.setCategory(category); // Присваиваем существующую категорию новой книге
        return bookRepository.save(book);
    }

    // PUT: Обновление данных книги
    @PutMapping("/{id}")
    public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return bookRepository.findById(id).map(book -> {
            // Проверяем и получаем существующую категорию
            Category category = categoryRepository.findByName(bookDetails.getCategory().getName())
                    .orElseGet(() -> categoryRepository.save(bookDetails.getCategory()));

            // Обновляем поля книги
            book.setTitle(bookDetails.getTitle());
            book.setAuthor(bookDetails.getAuthor());
            book.setCategory(category);

            // Сохраняем изменения
            bookRepository.save(book);
            return ResponseEntity.ok(book);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // DELETE: Удаление книги
    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }
}
