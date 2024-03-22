package com.example.server.Service;

import com.example.server.Models.Platform;
import com.example.server.Models.Publisher;
import com.example.server.Repository.PublisherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PublisherServiceImpl implements PublisherService {
    @Autowired
    private PublisherRepository publisherRepository;
    @Override
    public List<Publisher> getAllPublisher(){

        List<Publisher> allPublisher  = publisherRepository.getAllPublisher();
        List<Publisher> undeletedPublisher = new ArrayList<Publisher>();
        for(Publisher publisher :  allPublisher){
            if (!publisher.getDeleted()){
                undeletedPublisher.add(publisher);
            }
        }
        return undeletedPublisher;
    }
}
