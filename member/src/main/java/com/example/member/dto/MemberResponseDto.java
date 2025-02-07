package com.example.member.dto;

import lombok.Getter;

@Getter
public class MemberResponseDto {

    private final Long id;
    private final String content;

    public MemberResponseDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }
}