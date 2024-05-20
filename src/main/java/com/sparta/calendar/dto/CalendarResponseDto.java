package com.sparta.calendar.dto;


import com.sparta.calendar.repository.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {

    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public CalendarResponseDto(Calendar calendar) {
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.userName = calendar.getUserName();
        this.createdAt = calendar.getCreatedAt();
    }
}
