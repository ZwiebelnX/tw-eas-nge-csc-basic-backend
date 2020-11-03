package com.tw.csc.nge.backend.basicbackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import com.tw.csc.nge.backend.basicbackend.model.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
class UserControllerTest{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void should_register_user_when_register_given_user_info() throws Exception{
        UserDto userDTO =
                UserDto.builder().nickname("Chen_Test").email("sicong.chen@126.com").password("12345678").realName(
                        "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists());
    }

    @Test
    public void should_throw_exception_when_register_when_user_exist() throws Exception{
        UserDto userDTO =
                UserDto.builder().nickname("Chen_Onion").email("sicong.chen@126.com").password("12345678").realName(
                        "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isConflict());

        userDTO = UserDto.builder().nickname("Chen").email("sicong.chen@163.com").password("12345678").realName(
                "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isConflict());
    }

    @Test
    public void should_throw_exception_when_register_given_illegal_info() throws Exception{
        UserDto userDTO =
                UserDto.builder().nickname("").email("xxxx").password("12345678").realName(
                        "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42201"));
    }

}
