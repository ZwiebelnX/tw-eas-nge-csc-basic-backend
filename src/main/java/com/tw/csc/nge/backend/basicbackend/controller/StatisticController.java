package com.tw.csc.nge.backend.basicbackend.controller;

import com.tw.csc.nge.backend.basicbackend.model.dto.statistic.StatisticInfoDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.service.StatisticService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController{

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService){
        this.statisticService = statisticService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<StatisticInfoDto> getStatisticInfo(HttpSession httpSession){
        long userId = Long.parseLong(((UserDto)httpSession.getAttribute("userInfo")).getId());
        return statisticService.getStatisticInfo(userId);
    }
}
