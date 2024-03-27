package com.example.server.Repository;

import com.example.server.Models.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {
    @Query(value = "select * from comments where user_id = :userId", nativeQuery = true)
    List<Comments> getCommentsByUser(@Param("userId") Long userId);

    @Query(value = "select * from comments where product_id = :productId", nativeQuery = true)
    List<Comments> getCommentsByProduct(@Param("productId") Long productId);

   // @Query (value = "update comments \n" + "set content = :contents \n" + "where ")

}
