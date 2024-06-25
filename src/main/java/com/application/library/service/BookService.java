package com.application.library.service;

import com.application.library.entity.Book;
import com.application.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * Service Layer
 *
 */
@Service
public class BookService
{
    @Autowired
    private BookRepository bookRepository;

    /**
     * Find all the books
     * @return all books from the repository
     */
    public List<Book> findAllBooks()
    {
        return bookRepository.findAll();
    }
    /**
     * Find books by id
     * @return a book searched by id or Throw a Run time exception
     */
    public Book findBooksBysId(Long id)
    {
        Book book;
        book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        return book;
    }

    /**
     * Save a book
     * @param book takes a book to be saved
     */
    public void createBook(Book book)
    {
        bookRepository.save(book);
    }

    /**
     * update book saving results
     * @param book
     */
    public void updateBook(Book book)
    {
        bookRepository.save(book);
    }
    public void deleteBook(Long id)
    {
        Book book;
        book = bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
        bookRepository.deleteById(book.getId());
    }
}
