package com.sparta.calendar.controller;

import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.repository.Calendar;
import com.sparta.calendar.service.CalendarService;
import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class CalendarController {

    public final CalendarService calendarService;

    @PostMapping("/calendars")
    public ResponseEntity<CalendarResponseDto> postCalendar(@RequestBody CalendarRequestDto dto){
        Calendar calendar = calendarService.createCalendar(dto);
        CalendarResponseDto response = new CalendarResponseDto(calendar);
//        return ResponseEntity.ok().build();
        return ResponseEntity.ok().body(response);
    }


    // 여기에서 pwd 를 받는다는게 id 자리에 아이디 대신 비번이 들어가는것?
    // 아직 전체조회시 내림차순 정렬 해야함

//    private final Map<Long, Calendar> calendarList = new HashMap<>();

//    @PostMapping("/calendars")
//    public CalendarResponseDto createCalendar(@RequestBody CalendarRequestDto requestDto){
//        Calendar calendar = new Calendar(requestDto);
//
//        Long maxId = calendarList.size() > 0 ? Collections.max(calendarList.keySet()) + 1 : 1;
//        calendar.setPwd(maxId);
//
//        calendarList.put(calendar.getPwd(), calendar);
//
//        CalendarResponseDto caldendarResponseDto = new CalendarResponseDto(calendar);
//
//        return caldendarResponseDto;
//    }

    // 전체 조회
//    @GetMapping("/calendars")
//    public List<CalendarResponseDto> getCalendars(){
//        List<CalendarResponseDto> responseList = calendarList.values().stream().map(CalendarResponseDto::new).toList();
//
//        return responseList;
//    }
//
//    // 선택 조회
//    @GetMapping("/calendars/{pwd}")
//    public Calendar getCalendars(@PathVariable Long pwd){
//        if(calendarList.containsKey(pwd)){
//            return calendarList.get(pwd);
//        }else{
//            throw new IllegalArgumentException("chosen calendar does not exist");
//        }
//    }
//
//    // 수정
//    //선택한 일정의 할일 제목, 할일 내용, 담당자을 수정할 수 있습니다.
//    // 수정된 일정의 정보를 반환 받아 확인할 수 있습니다.
//    @PutMapping("/calendars/{pwd}")
//    public Calendar updateCalendar(@PathVariable Long pwd, @RequestBody CalendarRequestDto requestDto){
//        if(calendarList.containsKey(pwd)){
//            Calendar calendar = calendarList.get(pwd);
//
//            calendar.update(requestDto);
//            return calendarList.get(pwd);
//        } else{
//            throw new IllegalArgumentException("chosen calendar does not exist");
//        }
//    }
//
//    // 삭제
//    @DeleteMapping("/calendars/{pwd}")
//    public Long deleteCalendar(@PathVariable Long pwd){
//        if(calendarList.containsKey(pwd)){
//            calendarList.remove(pwd);
//            return pwd;
//        } else{
//            throw new IllegalArgumentException("chosen calendar does not exist");
//        }
//    }
}
