package com.example.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

//모든 응답 Dto 패키지에 추가
@Getter
@RequiredArgsConstructor
public class HelloResponseDto {
    private final String name;
    private final int amount;
}
