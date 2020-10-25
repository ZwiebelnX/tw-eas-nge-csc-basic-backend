package com.tw.csc.nge.backend.basicbackend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.model.dto.UserDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class IntegrationTests{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void should_register_user_when_register_given_user_info() throws Exception{
        UserDTO userDTO = UserDTO.builder()
                                 .nickname("Chen")
                                 .email("sicong.chen@163.com")
                                 .password("12345678")
                                 .realName("ChenSicong")
                                 .phone("15812418818")
                                 .build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists());
    }

}
