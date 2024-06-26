package com.application.library.repository;

import com.application.library.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access
 */
public interface AuthorRepository extends JpaRepository<Author, Long>
{

}
