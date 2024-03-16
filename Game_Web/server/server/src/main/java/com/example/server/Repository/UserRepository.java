package com.example.server.Repository;

import com.example.server.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository <User,Long>{
//    String getPlatformName(Long id);

    @Query(value = "select * from users where user_name = :name", nativeQuery = true)
    Optional<User> findByName(@Param("name") String name);
}
