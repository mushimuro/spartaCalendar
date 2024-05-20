package com.sparta.calendar;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CommonResponse<T> {
    private Integer statusCode;
    private String msg;
    private T data;
}
