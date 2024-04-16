package com.example.SpringBookstoreAPI.Service;

import com.example.SpringBookstoreAPI.Model.Book;
import com.example.SpringBookstoreAPI.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    @Autowired
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    //@Cacheable(value = "books", key = "#id")
    public Book getBookById(Long id) {
        // This method will only be executed if the result is not already cached
        return bookRepository.findById(id).orElse(null);
    }

    //@CacheEvict(value = "books", key = "#id")
    public void deleteBook(Long id) {
        // This method will remove the cached book entry (identified by the ID)
        bookRepository.deleteById(id);
    }

    //@CachePut(value = "books", key = "#book.id")
    public Book updateBook(Book book) {
        // This method will update the cached book entry with the new data
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        // Retrieve all books from the BookRepository
        return bookRepository.findAll();
    }

    public Book saveBook(Book book) {
        // Save the book using BookRepository and return the saved object
        return bookRepository.save(book);
    }
}
