package com.sparta.levelone.dto;

import lombok.Getter;

@Getter
public class BlogRequestDto {
    private String username;

    private String contents;

    private String title;

    private int password;
}