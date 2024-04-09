package com.example.server.Controller;

import com.example.server.DTO.AddCommentsDTO;
import com.example.server.Service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.logging.Logger;

@Controller
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    public static final Logger logger = Logger.getLogger("Comments");

    @PostMapping("/create")
    void addCommentsByProduct(@RequestBody AddCommentsDTO addCommentsDTO) {
        System.out.println(addCommentsDTO)
        ;
        logger.info("Them vao comments voi noi userId : " + addCommentsDTO.getUserId() + " san pham : " + addCommentsDTO.getProductId() + " voi noi dung la : " + addCommentsDTO.getContent());
        commentsService.addComments(addCommentsDTO.getUserId(), addCommentsDTO.getProductId(), addCommentsDTO.getContent());
    }
}
