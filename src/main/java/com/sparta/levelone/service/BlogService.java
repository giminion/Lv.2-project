package com.sparta.levelone.service;

import com.sparta.levelone.dto.BlogRequestDto;
import com.sparta.levelone.dto.BlogResponseDto;
import com.sparta.levelone.entity.Blog;
import com.sparta.levelone.repository.BlogRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // 게시글 작성 API
    public BlogResponseDto createBlog(BlogRequestDto requestDto) {
        // RequestDto -> Entity
        Blog blog = new Blog(requestDto);

        Blog saveBlog = blogRepository.save(blog);

        // Entity -> ResponseDto
        BlogResponseDto blogResponseDto = new BlogResponseDto(blog);

        return blogResponseDto;
    }

    // 전체 게시글 목록 조회 API
    public List<BlogResponseDto> getBlog() {
        return blogRepository.findAllByOrderByModifiedAtDesc().stream().map(BlogResponseDto::new).toList();
    }

    // 특정 게시글 목록 조회 API
    public Optional<Blog> getBlogById(Long id){
        return blogRepository.findById(id);
    }

    // 선택한 게시글 수정 API
    @Transactional
    public BlogResponseDto updateBlog(Long id, BlogRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        Blog blog = findBlog(id);

        if(blog.getPassword()==requestDto.getPassword()){
            blog.update(requestDto);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");
        }

        return new BlogResponseDto(blog);
    }

    // 선택한 게시글 삭제 API
    public String deleteBlog(Long id,BlogRequestDto requestDto) {
        // 해당 게시물이 DB에 존재하는지 확인
        Blog blog = findBlog(id);
        if(blog.getPassword()==requestDto.getPassword()){
            blogRepository.delete(blog);
        }else{
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");
        }
        return "삭제했습니다!";  // 삭제 성공을 알림
    }

    private Blog findBlog(Long id){
        return blogRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시물은 존재하지 않습니다.")
        );
    }
}
