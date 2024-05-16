package com.sparta.calendar.dto;


import com.sparta.calendar.entity.Calendar;
import lombok.Getter;

@Getter
public class CalendarResponseDto {

    private Long id;
    private String title;
    private String content;
    private String person;
    private String pwd;
    private int date;

    public CalendarResponseDto(Calendar calendar) {
        this.id = calendar.getId();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.person = calendar.getPerson();
        this.pwd = calendar.getPwd();
        this.date = calendar.getDate();
    }
}
