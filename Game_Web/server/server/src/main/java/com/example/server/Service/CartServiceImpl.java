package com.example.server.Service;

import com.example.server.DTO.OldCartsDTO;
import com.example.server.Models.User;
import com.example.server.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public List<OldCartsDTO> getHistory(String userName) {
        User a = userRepository.findByName(userName).get();

    }
//    @Autowired
//    private Cart
}
