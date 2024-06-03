package com.sparta.calendar.dto;


import com.sparta.calendar.entity.Calendar;
import com.sparta.calendar.entity.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentRequestDto {
    @NotBlank(message = "공백이거나 null인 것은 불가합니다.")
    private String commentContent;

    @NotBlank(message = "공백이거나 null인 것은 불가합니다.")
    private String username;


    public Comment toEntity(){
        return Comment.builder()
                .commentContent(commentContent)
                .username(username)
                .build();
    }
}