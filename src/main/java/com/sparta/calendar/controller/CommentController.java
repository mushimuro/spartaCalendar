package com.sparta.calendar.controller;

import com.sparta.calendar.CommonResponse;
import com.sparta.calendar.dto.CalendarRequestDto;
import com.sparta.calendar.dto.CalendarResponseDto;
import com.sparta.calendar.dto.CommentRequestDto;
import com.sparta.calendar.dto.CommentResponseDto;
import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import com.sparta.calendar.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calendars")
@AllArgsConstructor
public class CommentController {

    public final CommentService commentService;

    // 댓글 등록
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

    // 댓글 조회
    @GetMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> getComments(@PathVariable Long commentId) {
        Comment comment = commentService.getComment(commentId);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("search complete")
                .data(response)
                .build());
    }

    // 댓글 수정
    @PutMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponse<CommentResponseDto>> putCalendar(@PathVariable Long commentId, @RequestBody CommentRequestDto dto) {
        Comment comment = commentService.updateComment(commentId, dto);
        CommentResponseDto response = new CommentResponseDto(comment);
        return ResponseEntity.ok().body(CommonResponse.<CommentResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("edit complete")
                .data(response)
                .build());
    }

    // 댓글 삭제
    @DeleteMapping("/comments/{commentId}")
    public ResponseEntity<CommonResponse> deleteComment(@PathVariable Long commentId, @RequestBody CommentRequestDto dto) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().body(CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .msg("delete complete")
                .build());
    }
}
