package com.sparta.leveltwo.controller;

import com.sparta.leveltwo.dto.BlogRequestDto;
import com.sparta.leveltwo.dto.BlogResponseDto;
import com.sparta.leveltwo.service.BlogService;
import com.sparta.leveltwo.entity.Blog;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    // 게시글 작성 API
    @PostMapping("/blog")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        return blogService.createBlog(requestDto);
    }

    // 전체 게시글 목록 조회 API
    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {
        return blogService.getBlog();
    }

    // 특정 게시물 조회
    @GetMapping("/blog/{id}")
    public Optional<Blog> getBlogById(@PathVariable Long id){
        return blogService.getBlogById(id);
    }

    // 게시물 수정 API
    @PutMapping("/blog/{id}")
    public BlogResponseDto updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.updateBlog(id,requestDto);
    }

    // 게시물 삭제 API
    @DeleteMapping("/blog/{id}")
    public String deleteBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        return blogService.deleteBlog(id,requestDto);
    }
}