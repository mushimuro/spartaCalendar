package com.sparta.calendar.dto;


import com.sparta.calendar.entity.Calendar;
import lombok.Getter;

@Getter
public class CalendarResponseDto {

    private Long pwd;
    private String title;
    private String content;
    private String person;
    private int date;

    public CalendarResponseDto(Calendar calendar) {
        this.pwd = calendar.getPwd();
        this.title = calendar.getTitle();
        this.content = calendar.getContent();
        this.person = calendar.getPerson();
        this.date = calendar.getDate();
    }
}
