package com.sparta.calendar.dto;

import com.sparta.calendar.repository.Calendar;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalendarRequestDto {
    private String title;
    private String content;
    private String userName;
    private String pwd;

    public Calendar toEntity(){
        return Calendar.builder()
                .title(title)
                .content(content)
                .userName(userName)
                .pwd(pwd)
                .build();
    }
}
