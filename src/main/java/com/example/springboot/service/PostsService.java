package com.example.springboot.service;

import com.example.springboot.domain.posts.Posts;
import com.example.springboot.domain.posts.PostsRepository;
import com.example.springboot.web.dto.PostsListResponseDto;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Autowired
    private MongoTemplate mongoTemplate; //update에 사용

    @Transactional // 기능을 하나로 묶어서 실행(데이터 무결성)
    public Long Save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
//        posts.update(requestDto.getTitle(), requestDto.getContent());

        // update 쿼리 수행을 위해 MongoTemplate 사용
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Update update = new Update();

        update.set("title", requestDto.getTitle());
        update.set("content",requestDto.getContent());
        mongoTemplate.updateFirst(query, update, Posts.class);

        return id;
    }

    @Transactional(readOnly = true) //조회 기능만(속도 개선)
    public PostsResponseDto findById(Long id){
        Posts document = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id ="+id));

        return new PostsResponseDto(document);
    }

    @Transactional
    public Long delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        postsRepository.delete(posts);
        return id;
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAll() {

        return postsRepository.findAll().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }
}
