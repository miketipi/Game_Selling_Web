package com.example.server.Repository;

import com.example.server.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface UserRepository extends JpaRepository <User,Long>{
//    String getPlatformName(Long id);
}
