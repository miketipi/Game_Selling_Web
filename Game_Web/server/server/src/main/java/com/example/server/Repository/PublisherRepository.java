package com.example.server.Repository;

import com.example.server.Models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PublisherRepository extends JpaRepository<Publisher, Long> {
    @Query(value = "select * from publisher", nativeQuery = true)
    List<Publisher> getAllPublisher();
    @Query(value = "SELECT publisher_name from publisher where publisher.id = :id", nativeQuery = true)
    String getPublisherName(@Param("id") Long id);
}
