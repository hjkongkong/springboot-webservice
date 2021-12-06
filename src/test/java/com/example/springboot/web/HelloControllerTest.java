package com.example.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = HelloController.class)
public class HelloControllerTest  {

    @Autowired // 스프링이 관리하는 bean을 주입받는다.
    private MockMvc mvc; // 웹 API test

    @Test
    public void hello_리턴() throws Exception{
        String hello = "hello world";

        mvc.perform(get("/hello")) // HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform 결과 검증(200)
                .andExpect(content().string(hello)); // mvc.perform 결과 검증 - hello를 return하는지..
    }

    @Test
    public void helloDto_리턴() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto") // param 값은 string만 허용
                        .param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //json 응닶값을 필드별로 검증
                .andExpect(jsonPath("$.amount",is(amount)));
    }

}
