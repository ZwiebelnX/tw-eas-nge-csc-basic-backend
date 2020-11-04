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
public class StoreControllerTest{

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void should_return_store_info_when_get_store() throws Exception{
        mockMvc.perform(get("/stores/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.name").value("小陈什么都卖店"))
               .andExpect(jsonPath("$.description").value("想得到的东西都卖"));
    }


    @Test
    public void should_return_goods_list_by_store_when_get_store_goods() throws Exception{
        mockMvc.perform(get("/stores/1/goods?pageNum=1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.totalPages").value(1))
               .andExpect(jsonPath("$.data", hasSize(5)));
    }

    @Test
    public void should_throw_exception_when_get_param_illegal() throws Exception{
        mockMvc.perform(get("/stores/1/goods?pageNum=-1"))
               .andExpect(status().isUnprocessableEntity())
               .andExpect(jsonPath("$.errorCode").value("42202"));
    }

    @Test
    public void should_throw_exception_when_store_not_exist() throws Exception{
        mockMvc.perform(get("/stores/10/goods?pageNum=1"))
               .andExpect(status().isNotFound())
               .andExpect(jsonPath("$.errorCode").value("40406"));
    }
}
