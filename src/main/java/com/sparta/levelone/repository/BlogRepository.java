package com.sparta.levelone.repository;

import com.sparta.levelone.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlogRepository extends JpaRepository<Blog,Long> {
    List<Blog> findAllByOrderByModifiedAtDesc();

    Optional<Blog> findById(Long id);
}
