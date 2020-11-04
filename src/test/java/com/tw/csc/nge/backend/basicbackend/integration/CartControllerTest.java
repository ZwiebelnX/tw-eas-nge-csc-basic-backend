package com.tw.csc.nge.backend.basicbackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.AddToCartDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.login.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CartControllerTest{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private final MockHttpSession mockHttpSession = new MockHttpSession();

    @Test
    public void should_throw_exception_when_post_carts_without_login() throws Exception{
        AddToCartDto addToCartDto = AddToCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(addToCartDto)).session(mockHttpSession))
               .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_add_to_cart_when_post_carts() throws Exception{
        this.doLogin();
        AddToCartDto addToCartDto = AddToCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(addToCartDto)).session(mockHttpSession))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.amount").value(10));
    }

    @Test
    public void should_throw_error_when_post_carts_given_illegal_info() throws Exception{
        this.doLogin();
        AddToCartDto addToCartDto = AddToCartDto.builder().goodsId(null).amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(addToCartDto)).session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity());

        addToCartDto = AddToCartDto.builder().goodsId("1").amount(-1).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(addToCartDto)).session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_cart_list_when_get_carts() throws Exception{
        this.doLogin();
        mockMvc.perform(get("/carts?pageNum=1&pageSize=10").session(mockHttpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.data").exists());

    }

    @Test
    public void should_throw_exception_when_get_carts_without_login() throws Exception{
        mockMvc.perform(get("/carts").contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_throw_error_when_get_carts_given_illegal_param() throws Exception{
        this.doLogin();
        mockMvc.perform(get("/carts?pageNum=-1&pageSize=10").session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"));

        mockMvc.perform(get("/carts?pageNum=1&pageSize=-10").session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"));
    }


    private void doLogin() throws Exception{
        LoginDto loginDto = LoginDto.builder().loginName("sicong.chen@163.com").password("12345678").build();
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(loginDto)).session(mockHttpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.nickname").exists());
    }
}