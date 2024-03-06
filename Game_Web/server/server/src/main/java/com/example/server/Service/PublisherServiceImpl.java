package com.example.server.Service;

import com.example.server.Models.Publisher;
import com.example.server.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public List<Publisher> getAllPublisher(){
        return publisherRepository.getAllPublisher();

    }
}
