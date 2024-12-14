package com.example.newsservice.mapper;

import com.example.newsservice.dto.NewsDTO;
import com.example.newsservice.entity.News;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {UserMapper.class, CategoryMapper.class})
public interface NewsMapper {

    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "category.name", target = "categoryName")
    @Mapping(expression = "java(news.getComments() == null ? 0L : (long) news.getComments().size())", target = "commentCount")
    NewsDTO toDto(News news);

    @Mapping(target = "author", ignore = true)
    @Mapping(target = "category", ignore = true)
    @Mapping(target = "comments", ignore = true)
    News toEntity(NewsDTO newsDTO);
}
