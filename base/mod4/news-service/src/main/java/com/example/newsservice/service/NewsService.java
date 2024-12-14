package com.example.newsservice.service;

import com.example.newsservice.dto.NewsDTO;
import com.example.newsservice.entity.News;
import com.example.newsservice.exception.NewsNotFoundException;
import com.example.newsservice.mapper.CommentMapper;
import com.example.newsservice.mapper.NewsMapper;
import com.example.newsservice.repository.NewsRepository;
import com.example.newsservice.specification.NewsSpecifications;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsMapper newsMapper;

    private final CommentMapper commentMapper;

    public NewsService(NewsRepository newsRepository, NewsMapper newsMapper, CommentMapper commentMapper) {
        this.newsRepository = newsRepository;
        this.newsMapper = newsMapper;
        this.commentMapper = commentMapper;
    }

    public NewsDTO createNews(NewsDTO newsDTO) {
        News news = newsMapper.toEntity(newsDTO);
        return newsMapper.toDto(newsRepository.save(news));
    }

    public List<NewsDTO> getAllNews(int page, int size) {
        Page<News> newsPage = newsRepository.findAll(PageRequest.of(page, size));
        return newsPage.getContent()
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<NewsDTO> filterNews(String author, String category, int page, int size) {
        Specification<News> spec = Specification.where(null);

        if (author != null) {
            spec = spec.and(NewsSpecifications.hasAuthor(author));
        }
        if (category != null) {
            spec = spec.and(NewsSpecifications.hasCategory(category));
        }

        Page<News> newsPage = newsRepository.findAll(spec, PageRequest.of(page, size));
        return newsPage.getContent()
                .stream()
                .map(newsMapper::toDto)
                .collect(Collectors.toList());
    }

    public NewsDTO getNewsById(Long id) {
        News news = newsRepository.findById(id)
                .orElseThrow(() -> new NewsNotFoundException("News not found with id: " + id));
        NewsDTO newsDTO = newsMapper.toDto(news);
        newsDTO.setComments(news.getComments()
                .stream()
                .map(commentMapper::toDto)
                .collect(Collectors.toList()));
        return newsDTO;
    }
}