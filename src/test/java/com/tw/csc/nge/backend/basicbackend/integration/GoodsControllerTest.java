package com.tw.csc.nge.backend.basicbackend.integration;

import com.tw.csc.nge.backend.basicbackend.IntegrationTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@IntegrationTest
public class GoodsControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_goods_list_by_pages_when_get_goods() throws Exception{
        mockMvc.perform(get("/goods?pageNum=1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalPages").value(2))
               .andExpect(jsonPath("$.data", hasSize(10)));
    }

    @Test
    public void should_throw_exception_when_get_goods_given_illegal_page_num() throws Exception{
        mockMvc.perform(get("/goods?pageNum=-1"))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"))
               .andExpect(jsonPath("$.errorMessage").value("GET请求参数非法 && 额外信息：页码不能小于1"));
    }

}
