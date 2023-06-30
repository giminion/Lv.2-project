package com.sparta.levelone.controller;

import com.sparta.levelone.dto.BlogRequestDto;
import com.sparta.levelone.dto.BlogResponseDto;
import com.sparta.levelone.service.BlogService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @PostMapping("/blog")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {
        return blogService.getBlog();
    }

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id,requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        return blogService.deleteBlog(id);
    }
}