package com.example.tasktracker.mapper;

import com.example.tasktracker.dto.UserDto;
import com.example.tasktracker.model.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);
}
