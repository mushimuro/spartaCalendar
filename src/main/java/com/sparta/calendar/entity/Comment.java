package com.sparta.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @NotBlank
    @Column(length = 100)
    private String username;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "calendar_id")
    private Calendar calendar;

    @Builder
    public Comment(String commentContent, String username, Calendar calendar, User user) {
        this.user = user;
        this.calendar = calendar;
        this.commentContent = commentContent;
        this.username = username;
        this.createdAt = LocalDateTime.now();
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }
}
