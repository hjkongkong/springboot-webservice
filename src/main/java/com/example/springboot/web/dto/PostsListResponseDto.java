package com.example.springboot.web.dto;

import com.example.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsListResponseDto {
    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsListResponseDto(Posts document){
        this.id = document.getId();
        this.title = document.getTitle();
        this.content = document.getContent();
        this.author = document.getAuthor();
    }


}
