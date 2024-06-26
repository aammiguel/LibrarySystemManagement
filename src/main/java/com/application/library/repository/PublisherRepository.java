package com.application.library.repository;

import com.application.library.entity.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Data Access
 */
public interface PublisherRepository extends JpaRepository<Publisher, Long>
{
}
