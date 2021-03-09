package top.zyqin.spring.boot.basic.controller;


import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@Slf4j

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)

class BookControllerTest {

    @Resource
    //依赖注入
    private MockMvc mockMvc;


    @Test
    void saveBook() throws Exception{
        String book = "{\n" +
                "  \"id\": 1,\n" +
                "  \"author\": \"zyq\",\n" +
                "  \"title\": \"Spring Boot从入门到精通\",\n" +
                "  \"content\": \"Spring Boot从入门到精通\",\n" +
                "  \"createTime\": \"2021-03-07 21:54:10\",\n" +
                "  \"readers\": [{\"name\": \"aaa\",\"age\": 20},{\"name\": \"bbb\",\"age\": 19}]\n" +
                "}";
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders
                        .request(HttpMethod.POST,
                                "/api/v1/books")
                        .contentType("application/json")
                        .content(book)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())

                .andExpect(MockMvcResultMatchers.jsonPath("$.data.author").value("zyq"))

                .andExpect(MockMvcResultMatchers.jsonPath("$.data.readers[0].age").value(20))
                .andDo(print())
                .andReturn();
        result.getResponse().setCharacterEncoding("UTF-8");
        log.info(result.getResponse().getContentAsString());
    }
}