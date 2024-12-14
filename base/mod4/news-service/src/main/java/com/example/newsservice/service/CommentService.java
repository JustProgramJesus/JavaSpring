package com.example.newsservice.service;

import com.example.newsservice.dto.CommentDTO;
import com.example.newsservice.entity.Comment;
import com.example.newsservice.entity.News;
import com.example.newsservice.exception.NewsNotFoundException;
import com.example.newsservice.mapper.CommentMapper;
import com.example.newsservice.repository.CommentRepository;
import com.example.newsservice.repository.NewsRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final NewsRepository newsRepository;
    private final CommentMapper commentMapper;

    public CommentService(CommentRepository commentRepository, NewsRepository newsRepository, CommentMapper commentMapper) {
        this.commentRepository = commentRepository;
        this.newsRepository = newsRepository;
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getCommentsForNews(Long newsId) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + newsId));
        return news.getComments()
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList());
    }

    public CommentDTO createComment(Long newsId, CommentDTO commentDTO) {
        News news = newsRepository.findById(newsId)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + newsId));
        Comment comment = commentMapper.toEntity(commentDTO);
        comment.setNews(news);
        return commentMapper.toDto(commentRepository.save(comment));
    }
}
