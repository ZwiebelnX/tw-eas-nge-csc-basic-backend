package com.tw.csc.nge.backend.basicbackend.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import com.tw.csc.nge.backend.basicbackend.model.dto.login.LoginDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
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
public class UserCouponControllerTest{

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void should_get_coupon_list_when_get_coupons() throws Exception{
        UserDto userDTO =
                UserDto.builder().nickname("coupon_test_2").email("coupon_2@test.com").password("12345678").realName(
                        "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists());

        MockHttpSession httpSession = new MockHttpSession();
        LoginDto loginDto = LoginDto.builder().loginName("coupon_test_2").password("12345678").build();
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(loginDto)).session(httpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.nickname").exists());

        mockMvc.perform(get("/coupons?pageNum=1&pageSize=10").session(httpSession))
               .andExpect(jsonPath("$.data", hasSize(1)));
    }


    @Test
    public void should_get_company_a_coupon_after_register() throws Exception{
        UserDto userDTO =
                UserDto.builder().nickname("coupon_test_2").email("coupon_2@test.com").password("12345678").realName(
                        "ChenSicong").phone("15812418818").build();
        mockMvc.perform(post("/users").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(userDTO)))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.id").exists());

        MockHttpSession httpSession = new MockHttpSession();
        LoginDto loginDto = LoginDto.builder().loginName("coupon_test_2").password("12345678").build();
        mockMvc.perform(post("/login").contentType(MediaType.APPLICATION_JSON)
                                      .content(objectMapper.writeValueAsString(loginDto)).session(httpSession))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").exists())
               .andExpect(jsonPath("$.nickname").exists());

        mockMvc.perform(get("/coupons?pageNum=1&pageSize=10").session(httpSession))
               .andExpect(jsonPath("$.data[0].couponName").value("A公司优惠券"));
    }

}
