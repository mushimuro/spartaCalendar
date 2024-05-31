package com.sparta.calendar.dto;


import com.sparta.calendar.entity.Calendar;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CalendarResponseDto {

    private Long calendarId;
    private String title;
    private String content;
    private String userName;
    private LocalDateTime createdAt;

    public CalendarResponseDto(Calendar calendar) {
        this.calendarId = calendar.getId();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
//        this.userName = calendar.getUserName();
        this.createdAt = calendar.getCreatedAt();
    }
}
