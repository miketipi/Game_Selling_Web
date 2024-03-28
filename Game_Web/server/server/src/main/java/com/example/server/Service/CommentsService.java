package com.example.server.Service;

import com.example.server.Models.Comments;

import java.util.List;

public interface CommentsService {
    List<Comments> getAllCommentsByProduct(Long productId);
    List<Comments> getAllCommentsByUser(Long userId);
    void addComments(Long userId, Long productId, String content);
    void updateComments(Long userId, Long productId, String content);
}
