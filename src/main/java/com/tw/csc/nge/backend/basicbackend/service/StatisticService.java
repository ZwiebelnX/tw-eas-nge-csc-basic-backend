package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.statistic.ModifyStatisticDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.statistic.StatisticInfoDto;
import com.tw.csc.nge.backend.basicbackend.model.po.StatisticPo;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.StatisticRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.PoToDtoTransformer;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticService{

    private final UserService userService;

    private final StatisticRepo statisticRepo;

    public StatisticService(UserService userService, StatisticRepo statisticRepo){
        this.userService = userService;
        this.statisticRepo = statisticRepo;
    }

    public List<StatisticInfoDto> getStatisticInfo(long userId){
        UserPo userPo = userService.getUserPo(userId);
        if(!userPo.isAdmin()){
            throw new BusinessException(BusinessExceptionType.USER_NOT_ADMIN);
        }

        List<StatisticInfoDto> statisticInfoDtoList = new ArrayList<>();
        statisticRepo.findAll()
                     .forEach((value) -> statisticInfoDtoList.add(PoToDtoTransformer.statisticPoToStatisticInfoDto(value))
                     );

        return statisticInfoDtoList;

    }

    @Transactional
    public StatisticInfoDto modifyStatistic(long userId, ModifyStatisticDto modifyStatisticDto){
        UserPo userPo = userService.getUserPo(userId);
        if(!userPo.isAdmin()){
            throw new BusinessException(BusinessExceptionType.USER_NOT_ADMIN);
        }

        StatisticPo statisticPo =
                statisticRepo.findByName(modifyStatisticDto.getStatisticName())
                             .orElseThrow(() -> new BusinessException(BusinessExceptionType.STATISTIC_NOT_FOUND));
        statisticPo.setValue(statisticPo.getValue() + modifyStatisticDto.getModifyValue());

        statisticRepo.save(statisticPo);

        return PoToDtoTransformer.statisticPoToStatisticInfoDto(statisticPo);
    }
}
