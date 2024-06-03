package com.sparta.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

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

    @Column
    @Length(max = 200)
    @NotBlank
    private String title;

    @Column
    private String content;

    @NotBlank
    @Column(length = 100)
    private String username;

    @Column(nullable = false)
    @NotBlank
    private String pwd;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "calendar")
    private List<Comment> commentList = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Calendar(String title, String content, String pwd, String username, User user) {
        this.user = user;
        this.title = title;
        this.content = content;
        this.username = username;
        this.pwd = pwd;
        this.createdAt = LocalDateTime.now();
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
