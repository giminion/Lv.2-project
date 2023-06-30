package com.sparta.levelone.dto;

import com.sparta.levelone.entity.Blog;
import lombok.Getter;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String title;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.title = blog.getTitle();
    }

    public BlogResponseDto(Long id, String username, String contents, String title) {
        this.id = id;
        this.username = username;
        this.contents = contents;
        this.title = title;
    }
}