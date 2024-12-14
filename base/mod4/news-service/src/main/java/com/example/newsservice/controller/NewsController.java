package com.example.newsservice.controller;

import com.example.newsservice.dto.NewsDTO;
import com.example.newsservice.service.NewsService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
public class NewsController {
    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsDTO> getAllNews(
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String category,
            @RequestParam int page,
            @RequestParam int size) {
        if (author != null || category != null) {
            return newsService.filterNews(author, category, page, size);
        }
        return newsService.getAllNews(page, size);
    }

    @GetMapping("/{id}")
    public NewsDTO getNewsById(@PathVariable Long id) {
        return newsService.getNewsById(id);
    }

    @PostMapping
    public NewsDTO createNews(@RequestBody NewsDTO newsDTO) {
        return newsService.createNews(newsDTO);
    }
}