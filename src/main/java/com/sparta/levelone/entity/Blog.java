package com.sparta.levelone.entity;

import com.sparta.levelone.dto.BlogRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity // JPA가 관리할 Entitu 클래스 저장
@Getter
@Setter
@Table(name = "blog") //  매핑할 블로그 명 지정
@NoArgsConstructor
public class Blog extends Timestamped {

    @Id // 식별자
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment 걸어주기
    private Long id;

    @Column(name ="username", nullable = false)
    private String username;

    @Column(name = "contents", nullable = false, length = 500) // length의 디폴트는 255
    private String contents;

    @Column(name = "title", nullable = false, length = 300)
    private String title;

    @Column(name = "password" )
    private int password;

    public Blog(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
        this.password = requestDto.getPassword();
    }

    public void update(BlogRequestDto requestDto) {
        this.username = requestDto.getUsername();
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
