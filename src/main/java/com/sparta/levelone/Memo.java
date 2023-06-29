package com.sparta.levelone;

import lombok.*;

@Getter
@Setter
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