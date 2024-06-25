package com.application.library.service;

import com.application.library.entity.Publisher;
import com.application.library.repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service Layer
 */
@Service
public class PublisherService
{
    @Autowired
    private PublisherRepository publisherRepository;

    /**
     * Find all publishers
     * @return all publishers
     */
    public List<Publisher> findAllPublisher()
    {
        return publisherRepository.findAll();
    }

    /**
     * Find a publisher by id
     * @param id takes an id to look for publisher
     * @return a publisher
     */
    public Publisher findPublisherById(Long id)
    {
        Publisher publisher;
        publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Not publisher found"));
        return publisher;
    }

    /**
     * Create a new publisher
     * @param publisher takes a publisher to create
     */
    public void createPublisher(Publisher publisher)
    {
        publisherRepository.save(publisher);

    }

    /**
     * Update publisher
     * @param publisher takes a publisher to be udpdated
     */
    public void updatePublisher(Publisher publisher)
    {
        publisherRepository.save(publisher);

    }

    /**
     * Delete a publisher
     * @param id takes an id to be deleted
     */
    public void deletePuslibher(Long id)
    {
        Publisher publisher;
        publisher = publisherRepository.findById(id).orElseThrow(() -> new RuntimeException("Publisher not found"));
        publisherRepository.deleteById(publisher.getId());
    }





}
