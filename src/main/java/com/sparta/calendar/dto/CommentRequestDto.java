package com.sparta.calendar.dto;


import com.sparta.calendar.entity.Comment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    private String commentContent;
    private Long userId;

    public Comment toEntity(){
        return Comment.builder()
                .commentContent(commentContent)
                .userId(userId)
                .build();
    }
}
