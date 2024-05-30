package com.sparta.calendar.dto;

import com.sparta.calendar.entity.User;
import lombok.Getter;

@Getter
public class UserResponseDto {
    private String username;

    public UserResponseDto(User user) {
        this.username = user.getUsername();
    }
}
