package com.sparta.calendar.service;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.repository.Calendar;
import com.sparta.calendar.repository.CalendarRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    public Calendar createCalendar(CalendarRequestDto dto){
        var newCalendar = dto.toEntity();
        return calendarRepository.save(newCalendar);
    }

}
