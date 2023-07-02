package com.sparta.levelone.service;

import com.sparta.levelone.dto.BlogRequestDto;
import com.sparta.levelone.dto.BlogResponseDto;
import com.sparta.levelone.entity.Blog;
import com.sparta.levelone.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
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
        return blogRepository.findAll().stream().map(BlogResponseDto::new).toList();
    }

    @Transactional
    public Long updateBlog(Long id, BlogRequestDto requestDto) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);
        // memo 내용 수정
        blog.update(requestDto);

        return id;
    }

    public Long deleteBlog(Long id) {
        // 해당 메모가 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        // memo 삭제
        blogRepository.delete(blog);

        return id;
    }

    private Blog findBlog(Long id){
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}
