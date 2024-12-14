package com.example.newsservice.dto;

import java.util.List;

public class UserDTO {
    private Long id;
    private String username;
    private String password; // Только если нужно передавать пароль
    private List<NewsDTO> newsList; // Список новостей, созданных пользователем
    private List<CommentDTO> comments; // Список комментариев, оставленных пользователем

    // Геттеры и сеттеры
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<NewsDTO> getNewsList() { return newsList; }
    public void setNewsList(List<NewsDTO> newsList) { this.newsList = newsList; }

    public List<CommentDTO> getComments() { return comments; }
    public void setComments(List<CommentDTO> comments) { this.comments = comments; }
}

