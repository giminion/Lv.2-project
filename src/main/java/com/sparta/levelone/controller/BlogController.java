package com.sparta.levelone.controller;

import com.sparta.levelone.dto.BlogRequestDto;
import com.sparta.levelone.dto.BlogResponseDto;
import com.sparta.levelone.service.BlogService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BlogController {

    private final JdbcTemplate jdbcTemplate;

    public BlogController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostMapping("/blog")
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.createBlog(requestDto);
    }

    @GetMapping("/blog")
    public List<BlogResponseDto> getBlog() {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.getBlog();
    }

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody BlogRequestDto requestDto) {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.updateBlog(id,requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public Long deleteBlog(@PathVariable Long id) {
        BlogService blogService = new BlogService(jdbcTemplate);
        return blogService.deleteBlog(id);
    }
}