package com.sparta.leveltwo.entity;

import com.sparta.leveltwo.dto.BlogRequestDto;
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

    @Column(name ="author", nullable = false)
    private String author;

    @Column(name = "contents", nullable = false, length = 500) // length의 디폴트는 255
    private String contents;

    @Column(name = "title", nullable = false, length = 300)
    private String title;


    public Blog(BlogRequestDto requestDto, String username) {
        this.author = username;
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public void update(BlogRequestDto requestDto) {
        this.contents = requestDto.getContents();
        this.title = requestDto.getTitle();
    }

    public Long getId() {
        return id;
    }
}
