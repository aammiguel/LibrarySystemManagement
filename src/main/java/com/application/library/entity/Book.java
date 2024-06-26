package com.application.library.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Create columns and many to many relationships
     */
    @Column(name = "isbn", length = 50, nullable = false, unique = true)
    private String isbn;
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    @Column(name = "description", length = 250, nullable = false)
    private String description;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_authors",
    joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "author_id")})
    private Set<Author> authors = new HashSet<Author>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_categories",
    joinColumns = {@JoinColumn(name = "book_id")},
    inverseJoinColumns = {@JoinColumn(name = "category_id")})
    private Set<Category> categories = new HashSet<Category>();

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "books_publishers",
            joinColumns = {@JoinColumn(name = "book_id")},
            inverseJoinColumns = {@JoinColumn(name = "publisher_id")})
    private Set<Publisher> publishers = new HashSet<Publisher>();

    /**
     * Constructor for dummmy data
     * @param isbn takes an isbn
     * @param name takes a name
     * @param description takes a description
     */
    public Book(final String isbn,
                final String name,
                final String description)
    {
        this.isbn = isbn;
        this.name = name;
        this.description = description;
    }

    /**
     * Create bidireccional relationships to make sure to delete or add elementes at the same time
     *
     * @param publisher
     */
    public void removePublisher(Publisher publisher)
    {
        this.publishers.remove(publisher);
        publisher.getBooks().remove(publisher);
    }

    public void addPublihers(Publisher publisher)
    {
        this.publishers.add(publisher);
        publisher.getBooks().add(this);
    }
    public void removeAuthor(Author author)
    {
        this.authors.remove(author);
        author.getBooks().remove(author);
    }

    public void addAuthor(Author author)
    {
        this.authors.add(author);
        author.getBooks().add(this);
    }
    public void removeCategory(Category category)
    {
        this.categories.remove(category);
        category.getBooks().remove(category);
    }

    public void addCategory(Category category)
    {
        this.categories.add(category);
        category.getBooks().add(this);
    }
}
