package com.sparta.calendar.entity;

import com.sparta.calendar.dto.CalendarRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Calendar {

    private String title;
    private String content;
    private String person;
    private String pwd;
    private String date;

    public Calendar(CalendarRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.person = requestDto.getPerson();
        this.pwd = requestDto.getPwd();
        this.date = requestDto.getDate();
    }
}
