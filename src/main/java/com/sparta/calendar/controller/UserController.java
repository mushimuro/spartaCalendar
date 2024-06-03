package com.sparta.calendar.controller;

import com.sparta.calendar.CommonResponse;
import com.sparta.calendar.dto.AuthRequest;
import com.sparta.calendar.dto.AuthResponse;
import com.sparta.calendar.dto.SignupRequestDto;
import com.sparta.calendar.dto.UserResponseDto;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.entity.UserRoleEnum;
import com.sparta.calendar.jwt.JwtUtil;
import com.sparta.calendar.repository.UserRepository;
import com.sparta.calendar.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입 구현
    @ResponseBody
    @PostMapping("/users/signup")
    public ResponseEntity<CommonResponse<UserResponseDto>> signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(CommonResponse.<UserResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("signup complete")
                .build());
    }

    // 로그인 구현
    @PostMapping("/users/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
        try {
            User user = userService.login(authRequest.getUsername(), authRequest.getPassword());
            String username = user.getUsername();
            UserRoleEnum role = user.getRole();
            String token = jwtUtil.createToken(username, role);

            AuthResponse authResponse = new AuthResponse("로그인 성공", token);
            return new ResponseEntity<>(authResponse, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new AuthResponse("유효하지 않은 사용자 이름 혹은 비밀번호", null), HttpStatus.UNAUTHORIZED);
        }
    }
}