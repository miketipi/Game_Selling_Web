package com.example.server.Service;

import com.example.server.DTO.OldCartsDTO;

import java.util.List;

public interface CartService {
    List<OldCartsDTO> getHistory(String userName);
