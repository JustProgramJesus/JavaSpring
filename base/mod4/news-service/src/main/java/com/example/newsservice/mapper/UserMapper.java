package com.example.newsservice.mapper;

import com.example.newsservice.dto.UserDTO;
import com.example.newsservice.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "newsList", ignore = true)
    @Mapping(target = "comments", ignore = true)
    UserDTO toDto(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "newsList", ignore = true)
    @Mapping(target = "comments", ignore = true)
    User toEntity(UserDTO userDTO);
}
