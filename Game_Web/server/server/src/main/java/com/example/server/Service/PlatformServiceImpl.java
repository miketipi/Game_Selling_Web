package com.example.server.Service;

import com.example.server.Models.Platform;
import com.example.server.Repository.PlatformRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PlatformServiceImpl implements PlatformService {
    @Autowired
    private PlatformRepository platformRepository;

    @Override
    public List<Platform> getAllPlatform(){
        return platformRepository.getAllPlatform();
    }

}
