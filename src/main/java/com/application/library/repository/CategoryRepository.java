package com.application.library.repository;

import com.application.library.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data access
 */
public interface CategoryRepository extends JpaRepository<Category, Long>
{
}
