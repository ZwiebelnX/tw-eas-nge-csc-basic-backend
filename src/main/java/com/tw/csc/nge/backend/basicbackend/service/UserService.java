package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserRepo;
import com.tw.csc.nge.backend.basicbackend.utils.model.DtoToPoTransformer;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserDto registerUser(UserDto userDto){
        if(userRepo.existsByNicknameOrEmail(userDto.getNickname(), userDto.getEmail())){
            throw new BusinessException(BusinessExceptionType.USER_EXIST);
        }

        UserPo userPO = DtoToPoTransformer.userDtoToUserPo(userDto);

        userRepo.save(userPO);

        userDto.setId(String.valueOf(userPO.getId()));
        return userDto;
    }

    public UserPo getUserPo(long id){
        return userRepo.findById(id).orElseThrow(() -> new BusinessException(BusinessExceptionType.USER_NOT_FOUND));
    }
}
