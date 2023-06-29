package com.sparta.levelone.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BlogController {

    @GetMapping("/blog")  // 게시물 조회
    public String getMemo(){
        return "게시물 조회";
    }

    @PostMapping("/blog")  // 게시물 작성
    public String postmemo(){
        return "게시물 작성";
    }

    @PutMapping("/blog")
    public String putmemo(){
        return "PUT 메서드";
    }

    @DeleteMapping("/blog")  // 게시물 삭제
    public String deleteMemo(){
        return "게시물 삭제";
    }
}
