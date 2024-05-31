package com.sparta.calendar.entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "calendar")
public class Calendar {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "calendar_id", nullable = false)
    private Long id;

    private String title;
    private String content;
//    private String userName;
    private String pwd;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "calendar")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Calendar(String title, String content, String userName, String pwd) {
        this.title = title;
        this.content = content;
//        this.userName = userName;
        this.pwd = pwd;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

//    public void setUserName(String userName) {
//        this.userName = userName;
//    }
}
