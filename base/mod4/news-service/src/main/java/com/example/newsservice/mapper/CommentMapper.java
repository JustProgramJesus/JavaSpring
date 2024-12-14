package com.example.newsservice.mapper;

import com.example.newsservice.dto.CommentDTO;
import com.example.newsservice.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "authorUsername", source = "author.username") // Преобразование имени автора
    @Mapping(target = "newsId", source = "news.id") // Преобразование ID новости
    CommentDTO toDto(Comment comment);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "news", ignore = true)
    Comment toEntity(CommentDTO commentDTO);
}

