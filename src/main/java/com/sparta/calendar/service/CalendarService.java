package com.sparta.calendar.service;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.repository.CalendarRepository;
import com.sparta.calendar.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CalendarService {
    private final CalendarRepository calendarRepository;
    private final UserRepository userRepository;

    // post
    public Calendar createCalendar(CalendarRequestDto dto){
        User user = userRepository.findByUsername(dto.getUsername()).orElseThrow(NullPointerException::new);
        System.out.println(dto.getUsername());
        Calendar calendar = new Calendar(dto.getTitle(), dto.getContent(), dto.getUsername(), dto.getPwd(), user);
        System.out.println("일정 생성");
        user.getCalendarList().add(calendar);
        System.out.println("유저의 리스트에 저장");

        return calendarRepository.save(calendar);   // 여기에서 문제 발생
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
//        calendar.setUserName(dto.getUserName());

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
