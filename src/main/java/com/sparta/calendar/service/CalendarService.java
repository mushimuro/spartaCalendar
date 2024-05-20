package com.sparta.calendar.service;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.repository.Calendar;
import com.sparta.calendar.repository.CalendarRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;

    // post
    public Calendar createCalendar(CalendarRequestDto dto){
        var newCalendar = dto.toEntity();
        return calendarRepository.save(newCalendar);
    }

    // get one calendar
    public Calendar getCalendar(Long id){
       return calendarRepository.findById(id)
               .orElseThrow(IllegalArgumentException::new);
    }

    // get all
    public List<Calendar> getAllCalendars(){
        return calendarRepository.findAll(Sort.by("createdAt").descending());
    }

    // put
    public Calendar updateCalendar(Long calendarId, CalendarRequestDto dto){
        Calendar calendar = getCalendar(calendarId);

        // pwd check
        if(calendar.getPwd() != null && !calendar.getPwd().equals(dto.getPwd())){
            throw new IllegalArgumentException();
        }

        calendar.setTitle(dto.getTitle());
        calendar.setContent(dto.getContent());
        calendar.setUserName(dto.getUserName());

        return calendarRepository.save(calendar);
    }

    public void deleteCalendar(Long calendarId, String pwd){
        Calendar calendar = getCalendar(calendarId);
        // pwd check
        if(calendar.getPwd() != null && !calendar.getPwd().equals(pwd)){
            throw new IllegalArgumentException();
        }

        calendarRepository.deleteById(calendarId);
    }
}
