package com.example.mytube_forlecture.comment.controller;

import com.example.mytube_forlecture.comment.domain.Comment;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
public class CommentController {

    @PostMapping("/{videoId}")
    private void createComment(@PathVariable Long videoId, @RequestBody Comment comment) {
        System.out.println(videoId);
        System.out.println(comment.getText());

    }

}
