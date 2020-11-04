package com.tw.csc.nge.backend.basicbackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import com.tw.csc.nge.backend.basicbackend.model.dto.login.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class StatisticControllerTest{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_return_statistic_info_when_get_statistic() throws Exception{
        MockHttpSession mockHttpSession = doLogin();
        mockMvc.perform(get("/statistics").session(mockHttpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$", hasSize(3)));
    }

    @Test
    public void should_throw_exception_when_user_is_not_admin() throws Exception{
        MockHttpSession httpSession = new MockHttpSession();
        LoginDto loginDto = LoginDto.builder().loginName("coupon_test@test.com").password("1234").build();
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(loginDto)).session(httpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.nickname").exists());

        mockMvc.perform(get("/statistics").session(httpSession))
               .andExpect(status().isForbidden())
               .andExpect(jsonPath("$.errorCode").value("40301"));
    }

    @Test
    public void should_throw_exception_when_without_login() throws Exception{
        mockMvc.perform(get("/statistics"))
               .andExpect(status().isUnauthorized())
               .andExpect(jsonPath("$.errorCode").value("40101"));
    }

    private MockHttpSession doLogin() throws Exception{
        MockHttpSession httpSession = new MockHttpSession();
        LoginDto loginDto = LoginDto.builder().loginName("sicong.chen@163.com").password("12345678").build();
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(loginDto)).session(httpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.nickname").exists());

        return httpSession;
    }

}
