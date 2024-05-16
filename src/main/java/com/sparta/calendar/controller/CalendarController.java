package com.sparta.calendar.controller;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.entity.Calendar;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class CalendarController {

    private final Map<Long, Calendar> calendarList = new HashMap<>();

    @PostMapping("/calendars")
    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto requestDto){
        Calendar calendar = new Calendar(requestDto);

        Long maxId = calendarList.size() > 0 ? Collections.max(calendarList.keySet()) + 1 : 1;
        calendar.setId(maxId);

        calendarList.put(calendar.getId(), calendar);

        CalendarResponseDto caldendarResponseDto = new CalendarResponseDto(calendar);

        return caldendarResponseDto;
    }

    // 전체 조회
    @GetMapping("/calendars")
    public List<CalendarResponseDto> getCalendars(){
        List<CalendarResponseDto> responseList = calendarList.values().stream().map(CalendarResponseDto::new).toList();

        return responseList;
    }

    // 선택 조회
    @GetMapping("/calendars/{id}")
    public Calendar getCalendars(@PathVariable Long id, @RequestBody CalendarRequestDto requestDto){
//        List<CalendarResponseDto> responseList = calendarList.values().stream().map(CalendarResponseDto::new).toList();

        if(calendarList.containsKey(id)){
            return calendarList.get(id);
        }else{
            throw new IllegalArgumentException("chosen date does not exist");
        }
    }
}
