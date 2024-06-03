package com.sparta.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "공백이거나 null인 것은 불가합니다.")
    private String commentContent;
    private LocalDateTime createdAt;
//    private Long userId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Builder
    public Comment(String commentContent, Long userId) {
        this.commentContent = commentContent;
//        this.userId = userId;
        this.createdAt = LocalDateTime.now();
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
