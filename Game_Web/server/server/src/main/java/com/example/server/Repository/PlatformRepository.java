package com.example.server.Repository;

import com.example.server.Models.Platform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlatformRepository extends JpaRepository<Platform, Long> {
    @Query(value = "select * from platform", nativeQuery = true)
    List<Platform> getAllPlatform();
    @Query(value = "insert into platform (platform_name) values :platform_name", nativeQuery = true)
    void addNewPlatform(@Param("platform_name") String platform_name);
    @Query(value = "SELECT platform_name from platform where platform.id = :id", nativeQuery = true)
    String getPlatformName(@Param("id") Long id);
}
