package com.sparta.calendar.service;


import com.sparta.calendar.dto.SignupRequestDto;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.entity.UserRoleEnum;
import com.sparta.calendar.jwt.JwtUtil;
import com.sparta.calendar.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // 회원 조회
    public User getUser(String username ) {
        return userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
    }

    // ADMIN_TOKEN
    private final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    public User signup(SignupRequestDto requestDto) {
        String username = requestDto.getUsername();
        String password = passwordEncoder.encode(requestDto.getPassword());
//        String password = requestDto.getPassword();
        String nickname = requestDto.getNickname();
//        UserRoleEnum role = UserRoleEnum.USER;

        // 회원 중복 확인
        Optional<User> checkUsername = userRepository.findByUsername(username);
        if (checkUsername.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        // 사용자 ROLE 확인
        UserRoleEnum role = UserRoleEnum.USER;
        if (requestDto.isAdmin()) {
            if (!ADMIN_TOKEN.equals(requestDto.getAdminToken())) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }

        // 사용자 등록
        User user = new User(username, password, nickname, role);
        return userRepository.save(user);
    }

    public User login(String username, String password) {
        User user = userRepository.findByUsername(username).orElseThrow(IllegalArgumentException::new);
        if (user == null || !Objects.equals(user.getPassword(), password)) {
            throw new IllegalArgumentException("유효하지 않은 사용자 이름 혹은 잘못된 비밀번호");
        }
        return user;
    }
}