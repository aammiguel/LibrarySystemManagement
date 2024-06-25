package com.application.library.service;

import com.application.library.entity.Category;
import com.application.library.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service layer
 */
@Service
public class CategoryService
{
    @Autowired
    CategoryRepository categoryRepository;

    /**
     * Find all categories
     * @return all categories
     */
    public List<Category> findAllCategories()
    {
        return categoryRepository.findAll();
    }

    /**
     * find a category by id
     * @param id takes an id to find a category
     * @return a category
     */
    public Category findCategoryById(Long id)
    {
        final Category category;
        category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not found"));
        return category;
    }

    /**
     * Create a new category
     * @param category
     */
    public void createCategory(Category category)
    {
        categoryRepository.save(category);
    }

    /**
     * Update a category
     * @param category
     */
    public void updateCategory(Category category)
    {
        categoryRepository.save(category);
    }

    /**
     * Delete a category
     * @param id takes an id to find a category and delete it
     */
    public void deleteCategory(Long id)
    {
        Category category;
        category = categoryRepository.findById(id).orElseThrow(()-> new RuntimeException("Category not foudn"));
        categoryRepository.deleteById(category.getId());
    }

}
