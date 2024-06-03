package com.sparta.calendar.dto;

import com.sparta.calendar.entity.Calendar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarRequestDto {
    private String title;
    private String content;
    private String username;
    private String pwd;

    public Calendar toEntity(){
        return Calendar.builder()
                .title(title)
                .content(content)
                .pwd(pwd)
                .build();
    }
}
