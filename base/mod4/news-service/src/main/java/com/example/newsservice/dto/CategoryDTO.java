package com.example.newsservice.dto;

import java.util.List;

public class CategoryDTO {
    private Long id;
    private String name;
    private List<NewsDTO> news; // Список новостей в категории

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<NewsDTO> getNews() { return news; }
    public void setNews(List<NewsDTO> news) { this.news = news; }
}

