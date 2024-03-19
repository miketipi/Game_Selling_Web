package com.example.server.Controller;

import com.example.server.Models.Publisher;
import com.example.server.Service.PublisherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    @Autowired
    public PublisherService publisherService;
    @GetMapping(value = "/all")
    @ResponseBody
    List<Publisher> getAllPublisher(){
        return publisherService.getAllPublisher();
    }
}
