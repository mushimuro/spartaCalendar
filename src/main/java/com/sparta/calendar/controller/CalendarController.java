package com.sparta.calendar.controller;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.entity.Calendar;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalendarController {

    private final Map<String, Calendar> calendarList = new HashMap<>();

    @PostMapping("/calendars")
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto requestDto){
        Calendar calendar = new Calendar(requestDto);

//        String pwd;
        calendarList.put(calendar.getPwd(), calendar);

        CalendarResponseDto caldendarResponseDto = new CalendarResponseDto(calendar);

        return caldendarResponseDto;

    }
}
