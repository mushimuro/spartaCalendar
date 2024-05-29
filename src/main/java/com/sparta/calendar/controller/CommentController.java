package com.sparta.calendar.controller;

import com.sparta.calendar.CommonResponse;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.dto.CommentRequestDto;
import com.sparta.calendar.dto.CommentResponseDto;
import com.sparta.calendar.entity.Comment;
import com.sparta.calendar.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendars")
@AllArgsConstructor
public class CommentController {

    public final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CommonResponse<CommentResponseDto>> postCalendarComment(@RequestBody CommentRequestDto dto) {
        Comment comment = commentService.createComment(dto);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("comment added")
                .data(response)
                .build());
    }
}
