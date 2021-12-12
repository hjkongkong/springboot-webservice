package com.example.springboot.web;

import com.example.springboot.service.PostsService;
import com.example.springboot.web.dto.PostsListResponseDto;
import com.example.springboot.web.dto.PostsResponseDto;
import com.example.springboot.web.dto.PostsSaveRequestDto;
import com.example.springboot.web.dto.PostsUpdateRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // 초기화 되지 않은 final field, @notnull이 붙은 field에 생성자 생성
@RestController//Json 형태로 객체 데이터를 반환
public class PostsApiController {

    private final PostsService postsService;

    // 등록
    @PostMapping("/api/v1/posts")
    public Long Save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.Save(requestDto);
    }

    //수정
    @PutMapping("/api/v1/posts/{id}")
    public Long Update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long Delete(@PathVariable Long id){
        return postsService.delete(id);
    }

    @GetMapping("/api/v1/posts")
    public List<PostsListResponseDto> findAll() {
        return postsService.findAll();
    }

}
