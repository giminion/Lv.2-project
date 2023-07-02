package com.sparta.levelone.dto;

import com.sparta.levelone.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private Long id;
    private String username;
    private String contents;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public BlogResponseDto(Blog blog) {
        this.id = blog.getId();
        this.username = blog.getUsername();
        this.contents = blog.getContents();
        this.title = blog.getTitle();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}