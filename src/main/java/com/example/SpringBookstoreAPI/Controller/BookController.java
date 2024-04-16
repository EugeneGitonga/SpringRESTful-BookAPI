package com.example.SpringBookstoreAPI.Controller;

import com.example.SpringBookstoreAPI.Model.Book;
import com.example.SpringBookstoreAPI.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@EnableCaching

@RestController
@Controller
@RequestMapping("/") // Base URI for all handlers in this controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Cacheable(value = "allBooks")
    @GetMapping("/get/books")
    public List<Book> getAllBooks() {
        System.out.println("Attempting to fetch all books");  // Debug log
        return bookService.getAllBooks();
    }

    @Cacheable(key = "#id", value = "book")
    @GetMapping("/get/books/{id}")
    public Book getBookById(@PathVariable Long id) {
        System.out.println("Attempting to fetch book with ID: " + id);  // Debug log
        return bookService.getBookById(id);
    }
    @CachePut(key = "#book.id", value = "book")
    @CacheEvict(value = "allBooks", allEntries = true)
    @PostMapping("/post/books")
    public Book saveBook(@RequestBody Book book) {
        System.out.println("Attempting to add books");  // Debug log
        return bookService.saveBook(book);
    }
    @CacheEvict(key = "#id", value = {"book", "allBooks"})
    @DeleteMapping("/delete/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        System.out.println("Attempting to delete books with ID: " + id);  // Debug log
        bookService.deleteBook(id);
    }
}
