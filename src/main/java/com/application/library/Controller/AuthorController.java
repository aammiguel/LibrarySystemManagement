package com.application.library.Controller;

import com.application.library.entity.Author;
import com.application.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthorController
{
    @Autowired
    private AuthorService authorService;

    /**
     * find all authors
     * @param model takes a model view to add authors
     * @return authors screen
     */
    @GetMapping("/authors")
    public String listAuthors(Model model)
    {
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    /**
     * delete an author
     * @param id takes an id to find author
     * @param model takes a model view to add author
     * @return authors screen
     */
    @GetMapping("/remove-author/{id}")
    public String deleteAuthor(@PathVariable Long id, Model model)
    {
        authorService.deleteAuthor(id);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "authors";
    }

    /**
     * display update author screen
     * @param id takes an id to find author
     * @param model takes a model view to add author
     * @return update-author screen
     */
    @GetMapping("update-author/{id}")
    public String updateAuthor(@PathVariable Long id, Model model)
    {
        model.addAttribute("author", authorService.findAuthorById(id) );
        return "update-author";
    }

    /**
     * save author updated
     * @param id takes an id to find author
     * @param author takes author to be updated
     * @param bindingResult checks if there is any error
     * @param model takes a model view to add author
     * @return redirect authors
     */
    @PostMapping("/update-author/{id}")
    public String saveUpdateAuthor9(@PathVariable Long id, Author author, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "update-author";
        }
        authorService.updateAuthor(author);
        model.addAttribute("authors", authorService.findAllAuthors());
        return "redirect:/authors";
    }

    /**
     * directo to add author screen
     * @param author takes author to add
     * @return add author screen
     */
    @GetMapping("/add-author")
    public String showCreateAuthor(Author author)
    {
        return "add-author";
    }

    /**
     * add author
     * @param author takes an author to be created
     * @param bindingResult check if there are errors
     * @param model takes model view
     * @return redirect: author
     */
    @PostMapping("/save-author")
    public String saveAuthor(Author author, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "add-author";
        }
        authorService.createAuthor(author);
        model.addAttribute("author", authorService.findAllAuthors());
        return "redirect:/authors";
    }

}
