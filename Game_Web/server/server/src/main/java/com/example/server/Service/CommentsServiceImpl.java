package com.example.server.Service;

import com.example.server.Models.Comments;
import com.example.server.Repository.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public List<Comments> getAllCommentsByProduct(Long productId) {
        List<Comments> getAllComments = commentsRepository.findAllById(Collections.singleton(productId));
        List<Comments> undeletedComments = new ArrayList<>();
        for (Comments a : getAllComments) {
            if (a.getDeleted() == false) {
                undeletedComments.add(a);
            }
        }
        return undeletedComments;
    }

    @Override
    public List<Comments> getAllCommentsByUser(Long userId) {
        return commentsRepository.getCommentsByUser(userId);

    }

    @Override
    public void addComments(Long userId, Long productId, String content) {
        commentsRepository.addComments(userId, productId, content);
    }

    @Override
    public void updateComments(Long userId, Long productId, String content) {
        commentsRepository.updateComments(userId, content, productId);
    }
}
