package com.sparta.levelone;

import lombok.*;

@Getter
@Setter
@RequiredArgsConstructor // final 파라미터만 들어간 생성자 만들어줌
@AllArgsConstructor // 모든 파라미터 들어간 생성자 만듬
@NoArgsConstructor // 기본생성자 만듬
public class Memo {
    private String username;
    private String contents;
}

class Main{
    public static void main(String[] args) {
        Memo memo = new Memo();
        memo.setUsername("Robbie");
        System.out.println(memo.getUsername());
    }
}