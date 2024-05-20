package com.sparta.calendar.repository;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "calendar_id", nullable = false)
    private Long id;

    private String title;
    private String content;
    private String userName;
    private String pwd;
    private LocalDateTime createdAt;

    @Builder
    public Calendar(String title, String content, String userName, String pwd) {
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.pwd = pwd;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
