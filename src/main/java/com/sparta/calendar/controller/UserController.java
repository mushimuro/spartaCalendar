package com.sparta.calendar.controller;

import com.sparta.calendar.CommonResponse;
import com.sparta.calendar.dto.LoginRequestDto;
import com.sparta.calendar.dto.SignupRequestDto;
import com.sparta.calendar.dto.UserResponseDto;
import com.sparta.calendar.entity.User;
import com.sparta.calendar.entity.UserRoleEnum;
import com.sparta.calendar.jwt.JwtUtil;
import com.sparta.calendar.repository.UserRepository;
import com.sparta.calendar.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class UserController {

    private final JwtUtil jwtUtil;
    private final UserService userService;
    private final UserRepository userRepository;

    // 회원가입 구현
    @ResponseBody
    @PostMapping("/user/signup")
    public ResponseEntity<CommonResponse<UserResponseDto>> signup(@RequestBody SignupRequestDto signupRequestDto) {
        User user = userService.signup(signupRequestDto);
        return ResponseEntity.ok().body(CommonResponse.<UserResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("signup complete")
                .build());
    }

    //로그인 구현
    @ResponseBody
    @PostMapping("/user/login")
    public ResponseEntity<CommonResponse<UserResponseDto>> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        userService.login(loginRequestDto, response);
        return ResponseEntity.ok().body(CommonResponse.<UserResponseDto>builder()
                .statusCode(HttpStatus.OK.value())
                .msg("login complete")
                .build());
    }

//    // jwt 생성
//    @GetMapping("/create-jwt")
//    public String createJwt(HttpServletResponse res){
//        String token = jwtUtil.createToken("qwe123", UserRoleEnum.USER);
//        jwtUtil.addJwtToCookie(token, res);
//
//        return "createJwt : " + token;
//    }
//
//    @GetMapping("/get-jwt")
//    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
//        // JWT 토큰 substring
//        String token = jwtUtil.substringToken(tokenValue);
//
//        // 토큰 검증
//        if(!jwtUtil.validateToken(token)){
//            throw new IllegalArgumentException("Token Error");
//        }
//
//        // 토큰에서 사용자 정보 가져오기
//        Claims info = jwtUtil.getUserInfoFromToken(token);
//        // 사용자 username
//        String username = info.getSubject();
//        System.out.println("username = " + username);
//        // 사용자 권한
//        String authority = (String) info.get(JwtUtil.AUTHORIZATION_KEY);
//        System.out.println("authority = " + authority);
//
//        return "getJwt : " + username + ", " + authority;
//    }
}
