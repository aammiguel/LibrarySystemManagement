package com.application.library.service;

import com.application.library.entity.Author;
import com.application.library.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer
 */
@Service
public class AuthorService
{
    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Find all the authors
     * @return all authorss
     */
    public List<Author> findAllAuthors()
    {
        return authorRepository.findAll();
    }

    /**
     * Find an author by id
     * @param id takes an author id
     * @return an author
     */
    public Author findAuthorById(Long id)
    {
        Author author;
        author = authorRepository.findById( id ).orElseThrow(() -> new RuntimeException("Author not found"));

        return author;
    }

    /**
     * Create a new author
     * @param author takes an author to be created
     */
    public void createAuthor(Author author)
    {
        authorRepository.save(author);
    }

    /**
     * Update an author
     * @param author takes an author to be updated
     */
    public void updateAuthor(Author author)
    {
        authorRepository.save(author);
    }

    /**
     * Delete an author
     * @param id takes an id to be deleted
     */
    public void deleteAuthor(Long id)
    {
        Author author;
        author = authorRepository.findById(id).orElseThrow(() -> new RuntimeException("Author not found"));
        authorRepository.deleteById(author.getId());

    }

}
