package com.sparta.calendar.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Length(min=4, max=10, message="id는 4~10자 사이입니다.")
    @Pattern(regexp = "[a-z0-9]*$",message = "아이디 형식이 일치하지 않습니다.")
    @Column(nullable = false, unique = true)
    private String username;

    @Length(min = 8, max = 15, message="비밀번호는 8~15자 사이입니다.")
    @Pattern(regexp = "[a-zA-Z0-9]*$",message = "비밀번호 형식이 일치하지 않습니다.")
    // 비밀번호가 암호화 되면서 위 두 조건을 만족 못한다. 따라서 계속 오류가 발생한다.
    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum role;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<Comment> commentList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Calendar> calendarList = new ArrayList<>();

    public User(String username, String password, String nickname, UserRoleEnum role) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.role = role;
        this.createdAt = LocalDateTime.now();
    }

    @Builder
    public User(String username){
        this.username = username;
    }
}