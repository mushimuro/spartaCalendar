package com.sparta.calendar.controller;

import com.sparta.calendar.CommonResponse;
import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.service.CalendarService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/calendars")
@AllArgsConstructor
public class CalendarController {

    public final CalendarService calendarService;


    // 일정 등록
    @PostMapping
    public ResponseEntity<CommonResponse<CalendarResponseDto>> postCalendar(@RequestBody CalendarRequestDto dto) {
        Calendar calendar = calendarService.createCalendar(dto);
        CalendarResponseDto response = new CalendarResponseDto(calendar);
        return ResponseEntity.ok().body(CommonResponse.<CalendarResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("creation complete")
                .data(response)
                .build());
    }

    // 선택 일정 조회
    @GetMapping("/{calendarId}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> getCalendars(@PathVariable Long calendarId) {
        Calendar calendar = calendarService.getCalendar(calendarId);
        CalendarResponseDto response = new CalendarResponseDto(calendar);
        return ResponseEntity.ok().body(CommonResponse.<CalendarResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("search complete")
                .data(response)
                .build());
    }

    // 전체 일정 조회
    @GetMapping
    public ResponseEntity<CommonResponse<List<CalendarResponseDto>>> getAllCalendars() {
        List<Calendar> calendarList = calendarService.getAllCalendars();
        List<CalendarResponseDto> response = calendarList.stream().map(CalendarResponseDto::new).toList();
        return ResponseEntity.ok().body(CommonResponse.<List<CalendarResponseDto>>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("search all complete")
                .data(response)
                .build());
    }

    // 일정 수정
    @PutMapping("/{calendarId}")
    public ResponseEntity<CommonResponse<CalendarResponseDto>> putCalendar(@PathVariable Long calendarId, @RequestBody CalendarRequestDto dto) {
        Calendar calendar = calendarService.updateCalendar(calendarId, dto);
        CalendarResponseDto response = new CalendarResponseDto(calendar);
        return ResponseEntity.ok().body(CommonResponse.<CalendarResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("edit complete")
                .data(response)
                .build());
    }

    // 일정 삭제
    @DeleteMapping("/{calendarId}")
    public ResponseEntity<CommonResponse> deleteCalendar(@PathVariable Long calendarId, @RequestBody CalendarRequestDto dto) {
        calendarService.deleteCalendar(calendarId, dto.getPwd());
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("delete complete")
                .build());
    }


}