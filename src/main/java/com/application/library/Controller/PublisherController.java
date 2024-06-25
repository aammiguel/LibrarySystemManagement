package com.application.library.Controller;

import com.application.library.entity.Publisher;
import com.application.library.service.PublisherService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublisherController
{
    @Autowired
    private PublisherService publisherService;

    /**
     * find all publishers
     * @param model takes model attribute to add publishers
     * @return publishers screen
     */
    @GetMapping("/publishers")
    public String findAllPublishers(Model model)
    {
        model.addAttribute("publishers", publisherService.findAllPublisher());
        return "publishers";
    }

    /**
     * Delete a publisher
     * @param id takes an id to find publisher
     * @param model takes model attribute to add publisher
     * @return publisher screen updated
     */
    @GetMapping("/remove-publisher/{id}")
    public String deletePublisher(@PathVariable Long id, Model model)
    {
        publisherService.deletePuslibher(id);
        model.addAttribute("publishers", publisherService.findAllPublisher());
        return "publishers";
    }

    /**
     * update publisher
     * @param id takes an id to find publisher
     * @param model takes a model view to add publisher
     * @return update publisher screen
     */
    @GetMapping("update-publisher/{id}")
    public String updatePuslibher(@PathVariable Long id,  Model model)
    {
        model.addAttribute("publisher", publisherService.findPublisherById(id));
        return "update-publisher";
    }

    /**
     * update publisher
     * @param id takes an id to find publisher
     * @param publisher takes a publisher to update
     * @param bindingResult check if there's any error
     * @param model takes a model view to add publisher
     * @return redirect publishers
     */
    @PostMapping("/update-publisher/{id}")
    public String saveUpdatePublisher(@PathVariable Long id, Publisher publisher, BindingResult bindingResult, Model model)
    {
        if(bindingResult.hasErrors())
        {
            return "update-publisher";
        }
        publisherService.updatePublisher(publisher);
        model.addAttribute("publisher", publisherService.findAllPublisher());
        return "redirect:/publishers";
    }

    /**
     * create a mapping for add publsierh view
     * @param publisher takes a publisher to ve updated
     * @return add publisher screen
     */
    @GetMapping("/add-publisher")
    public String showCreatePage(Publisher publisher)
    {
        return "add-publisher";
    }

    /**
     * save publisher created
     * @param publisher takes a publisher to update
     * @param bindingResult checks if there's any errors
     * @param model takes a model view to add publisher
     * @return redirect publishers
     */
    @PostMapping("/save-publisher")
    public String createPublisher(Publisher publisher, BindingResult bindingResult, Model model )
    {
        if(bindingResult.hasErrors())
        {
            return "add-publisher";
        }
        publisherService.createPublisher(publisher);
        model.addAttribute("publishers", publisherService.findAllPublisher());
        return "redirect:/publishers";
    }
}
