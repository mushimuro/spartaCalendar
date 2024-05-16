package com.sparta.calendar.entity;

import com.sparta.calendar.dto.CalendarRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Calendar {

    private Long pwd;
    private String title;
    private String content;
    private String person;
    private int date;

    public Calendar(CalendarRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.person = requestDto.getPerson();
        this.date = requestDto.getDate();
    }

    public void update(CalendarRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.content = requestDto.getContent();
        this.person = requestDto.getPerson();
    }
}
