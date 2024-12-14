package com.example.newsservice.dto;

public class CommentDTO {
    private Long id;
    private String content;
    private String authorUsername; // Имя автора комментария
    private Long newsId; // ID новости

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public String getAuthorUsername() { return authorUsername; }
    public void setAuthorUsername(String authorUsername) { this.authorUsername = authorUsername; }

    public Long getNewsId() { return newsId; }
    public void setNewsId(Long newsId) { this.newsId = newsId; }
}


