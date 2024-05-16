package com.sparta.calendar.dto;

import lombok.Getter;

@Getter
public class CalendarRequestDto {
    private String title;
    private String content;
    private String person;
    private int date;
}
