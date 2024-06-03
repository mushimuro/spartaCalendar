package com.sparta.calendar.dto;

import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private Long id;
    private String commentContent;
    private LocalDateTime createdAt;

    public CommentResponseDto(Comment comment) {
        this.id = comment.getId();
        this.commentContent = comment.getCommentContent();
        this.createdAt = comment.getCreatedAt();
    }
}
