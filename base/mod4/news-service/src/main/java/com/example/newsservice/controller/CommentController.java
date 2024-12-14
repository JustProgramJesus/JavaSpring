package com.example.newsservice.controller;

import com.example.newsservice.dto.CommentDTO;
import com.example.newsservice.service.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{newsId}")
    public List<CommentDTO> getCommentsForNews(@PathVariable Long newsId) {
        return commentService.getCommentsForNews(newsId);
    }

    @PostMapping("/{newsId}")
    public CommentDTO createComment(@PathVariable Long newsId, @RequestBody CommentDTO commentDTO) {
        return commentService.createComment(newsId, commentDTO);
    }
}
