package com.example.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor // 기본 생성자 자동 추가
@Document(collection="posts")
public class Posts {
    @Transient // 영속성 필드 제외
    public static final String SEQUENCE_NAME = "users_sequence";
    // 자동 증가 시퀀스에 대해 참조하는 SEQUENCE_NAME 선언

    @Id
    private Long id;
    private String title;
    private String content;
    private String author;

    public void setId(Long id){
        this.id = id;
    }

    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }


}
