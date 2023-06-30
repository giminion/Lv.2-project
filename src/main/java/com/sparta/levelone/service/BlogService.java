package com.sparta.levelone.service;

import com.sparta.levelone.dto.BlogRequestDto;
import com.sparta.levelone.dto.BlogResponseDto;
import com.sparta.levelone.entity.Blog;
import com.sparta.levelone.repository.BlogRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(JdbcTemplate jdbcTemplate) {
        this.blogRepository = new BlogRepository(jdbcTemplate);
    }

    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }

    public List<BlogResponseDto> getBlog() {
        // DB 조회
        return blogRepository.findAll();
    }

    public Long updateBlog(Long id, BlogRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = blogRepository.findById(id);
        if(blog != null) {
            // memo 내용 수정
            blogRepository.update(id,requestDto);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }

    public Long deleteBlog(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = blogRepository.findById(id);
        if(blog != null) {
            // memo 삭제
            blogRepository.delete(id);

            return id;
        } else {
            throw new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.");
        }
    }


}
