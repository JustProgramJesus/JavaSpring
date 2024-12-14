package com.example.newsservice.mapper;

import com.example.newsservice.dto.CategoryDTO;
import com.example.newsservice.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    @Mapping(target = "news", ignore = true)
    CategoryDTO toDto(Category category);

    @Mapping(target = "news", ignore = true)
    Category toEntity(CategoryDTO categoryDTO);
}
