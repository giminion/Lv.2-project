package com.sparta.leveltwo.dto;

import com.sparta.leveltwo.entity.Blog;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private String title;
    private String author;
    private String content;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private Long id;

    public BlogResponseDto(Blog blog) {
        this.author = blog.getAuthor();
        this.content = blog.getContent();
        this.title = blog.getTitle();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
        this.id = blog.getId();
    }
}