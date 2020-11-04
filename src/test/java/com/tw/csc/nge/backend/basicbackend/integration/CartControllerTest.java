package com.tw.csc.nge.backend.basicbackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import com.tw.csc.nge.backend.basicbackend.model.dto.cart.ModifyCartDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.login.LoginDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class CartControllerTest{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_throw_exception_when_post_carts_without_login() throws Exception{
        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)))
               .andExpect(status().isUnauthorized());
    }

    @Test
    public void should_add_to_cart_when_post_carts() throws Exception{
        MockHttpSession mockHttpSession = this.doLogin();

        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)).session(mockHttpSession))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.amount").value(10));
    }

    @Test
    public void should_throw_error_when_post_carts_given_illegal_info() throws Exception{
        MockHttpSession mockHttpSession = this.doLogin();

        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId(null).amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)).session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity());

        modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(-1).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)).session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void should_return_cart_list_when_get_carts() throws Exception{
        MockHttpSession mockHttpSession = this.doLogin();

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
        MockHttpSession mockHttpSession = this.doLogin();

        mockMvc.perform(get("/carts?pageNum=-1&pageSize=10").session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"));

        mockMvc.perform(get("/carts?pageNum=1&pageSize=-10").session(mockHttpSession))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"));
    }

    @Test
    public void should_reduce_goods_amount_from_cart_when_delete_cart() throws Exception{
        MockHttpSession mockHttpSession = this.doLogin();

        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)).session(mockHttpSession))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.amount").value(10));

        modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(5).build();
        mockMvc.perform(delete("/carts").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(modifyCartDto))
                                        .session(mockHttpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.amount").value(5));
    }

    @Test
    public void should_remove_goods_from_cart_when_amount_is_zero_when_delete_cart() throws Exception{
        MockHttpSession mockHttpSession = this.doLogin();
        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(10).build();
        mockMvc.perform(post("/carts").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(modifyCartDto)).session(mockHttpSession))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.amount").value(10));

        mockMvc.perform(delete("/carts").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(modifyCartDto))
                                        .session(mockHttpSession))
               .andExpect(status().isNoContent());
    }


    @Test
    public void should_throw_exception_when_delete_cart_without_login() throws Exception{
        ModifyCartDto modifyCartDto = ModifyCartDto.builder().goodsId("1").amount(10).build();

        mockMvc.perform(delete("/carts").contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(modifyCartDto)))
               .andExpect(status().isUnauthorized());
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