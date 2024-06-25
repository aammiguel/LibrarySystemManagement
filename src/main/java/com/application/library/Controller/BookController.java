package com.application.library.Controller;

import com.application.library.entity.Book;
import com.application.library.entity.Category;
import com.application.library.service.AuthorService;
import com.application.library.service.BookService;
import com.application.library.service.CategoryService;
import com.application.library.service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BookController
{
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private PublisherService publisherService;
    @Autowired
    private AuthorService authorService;

    /**
     * Book mapping, home screen
     * @param model display the model view
     * @return the screen
     */
    @GetMapping("/books")
    public String findAllBooks( Model model)
    {
        List<Book> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        return "books";
    }
    /**
     * List of books mapping, home screen
     * @param model display the model view
     * @param id takes an id to identify books
     * @return the screen list of books
     */
    @GetMapping("/book/{id}")
    public String findBook(@PathVariable Long id, Model model)
    {
        Book book = bookService.findBooksBysId(id);
        model.addAttribute("book", book);
        return "list-book";
    }
    /**
     * Remove book maping, home screen
     * @param model display the model view
     * @param id takes an id to identify books
     * @return the screen
     */
    @GetMapping("remove-book/{id}")
    public String deleteBook(@PathVariable Long id, Model model)
    {
        bookService.deleteBook(id);
        model.addAttribute("books", bookService.findAllBooks());
        return "books";
    }

    /**
     * update book mapping
     * @param id takes an id to indentify books
     * @param model display model view
     * @return update book screen
     */
    @GetMapping("/update-book/{id}")
    public String updateBook(@PathVariable Long id, Model model)
    {
        Book book = bookService.findBooksBysId(id);
        model.addAttribute("book", book);
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublisher());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "update-book";
    }

    /**
     * save book mapping
     * @param id takes an id to identify books
     * @param result takes a result in case of errors
     * @param model takes a model view
     * @return
     */
    @PostMapping("/save-update/{id}")
    public String updateBook(@PathVariable Long id, Book book, BindingResult  result, Model model)
    {
        if(result.hasErrors())
        {
            return "update-book";
        }
        bookService.updateBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }

    /**
     * add book mapping
     * @param model display model view
     * @return update book screen
     */
    @GetMapping("/add-book")
    public String addBook(Book book, Model model)
    {
        model.addAttribute("categories", categoryService.findAllCategories());
        model.addAttribute("publishers", publisherService.findAllPublisher());
        model.addAttribute("authors", authorService.findAllAuthors());
        return "add-book";
    }
    /**
     * Post book mapping
     * @param model display model view
     * @return update book screen
     */
    @PostMapping("/save-book")
    public String saveBook( Book book, BindingResult  result, Model model)
    {
        if(result.hasErrors())
        {
            return "add-book";
        }
        bookService.createBook(book);
        model.addAttribute("books", bookService.findAllBooks());
        return "redirect:/books";
    }



}
