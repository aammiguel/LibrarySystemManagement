package com.application.library.Controller;

import com.application.library.entity.Category;
import com.application.library.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoryController
{
    @Autowired
    private CategoryService categoryService;

    /**
     * GetMapping to create a categories model
     * @param model takes a model to be added
     * @return categories screen
     */
    @GetMapping("/categories")
    public String findAllCategory(Model model)
    {
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    /**
     * Getmapping to delete a a category
     * @param id takes an id to find the category
     * @param model create a model
     * @return categories view
     */
    @GetMapping("/remove-category/{id}")
    public String deleteCategory(@PathVariable Long id, Model model)
    {
        categoryService.deleteCategory(id);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "categories";
    }

    /**
     * update category
     * @param id takes an id to find a category
     * @param model creates a model view
     * @return update category screen
     */
    @GetMapping("update-category/{id}")
    public String updateCategory(@PathVariable Long id, Model model)
    {
        model.addAttribute("category", categoryService.findCategoryById(id));
        return "update-category";
    }

    @PostMapping("/update-category/{id}")
    public String saveCategory(@PathVariable Long id, Category category, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "update-category";
        }
        categoryService.updateCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }

    /**
     * Add a new category
     * @param category takes a category
     * @return add-category screen
     */
    @GetMapping("/add-category")
    public String showCreateCategory(Category category )
    {
        return "add-category";
    }

    /**
     * save category
     * @param category takes a category to add
     * @param bindingResult check if has errors
     * @param model add model view
     * @return caterogies view
     */
    @PostMapping("/save-category")
    public String saveCategory(Category category, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "add-categories";
        }
        categoryService.createCategory(category);
        model.addAttribute("categories", categoryService.findAllCategories());
        return "redirect:/categories";
    }



}
