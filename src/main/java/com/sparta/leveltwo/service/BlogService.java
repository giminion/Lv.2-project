package com.sparta.leveltwo.service;

import com.sparta.leveltwo.dto.BlogRequestDto;
import com.sparta.leveltwo.dto.BlogResponseDto;
import com.sparta.leveltwo.dto.MessageResponseDto;
import com.sparta.leveltwo.entity.Blog;
import com.sparta.leveltwo.jwt.JwtUtil;
import com.sparta.leveltwo.repository.BlogRepository;
import io.jsonwebtoken.Claims;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private BlogRepository blogRepository;

    private JwtUtil jwtUtil;

    public BlogService(BlogRepository blogRepository, JwtUtil jwtUtil) {
        this.blogRepository = blogRepository;
        this.jwtUtil = jwtUtil;
    }

    // 게시글 작성 API
    public BlogResponseDto createBlog(String tokenValue, BlogRequestDto requestDto) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // username
        String username = info.getSubject();

        // RequestDto -> Entity
        Blog blog = new Blog(requestDto,username);

        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(saveBlog);

        return blogResponseDto;
    }

    // 전체 게시글 목록 조회 API
    public List<BlogResponseDto> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    // 특정 게시글 목록 조회 API
    public BlogResponseDto getBlogById(Long id){
        // 해당 게시글 존재하는지 확인
        Blog blog = findBlog(id);
        return new BlogResponseDto(blog);
    }

    // 선택한 게시글 수정 API
    @Transactional
    public BlogResponseDto updateBlog(String tokenValue, Long id, BlogRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // username
        String username = info.getSubject();

        if(!username.equals(blog.getAuthor())) {
            throw new IllegalArgumentException("해당 게시물을 작성한 사용자가 아닙니다.");
        }

        // post 수정(영속성 컨텍스트의 변경감지를 통해, 즉, requestDto에 들어온 객체로 post 객체(entity)를 업데이트 시킴)
        blog.update(requestDto);

        return new BlogResponseDto(blog);
    }

    // 선택한 게시글 삭제 API
    public ResponseEntity<MessageResponseDto> deleteBlog(String tokenValue, Long id) {
        // 해당 게시물이 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);

        // 토큰 검증
        if(!jwtUtil.validateToken(token)) {
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // username
        String username = info.getSubject();

        if(!username.equals(blog.getAuthor())) {
            throw new IllegalArgumentException("해당 게시물을 작성한 사용자가 아닙니다.");
        }

        return new ResponseEntity<MessageResponseDto>(new MessageResponseDto("게시물 삭제 성공", "200"), HttpStatus.OK);
    }

    private Blog findBlog(Long id){
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}
