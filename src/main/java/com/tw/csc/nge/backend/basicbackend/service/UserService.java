package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.UserExistException;
import com.tw.csc.nge.backend.basicbackend.model.dto.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserRepo;
import org.springframework.stereotype.Service;

@Service
public class UserService{

    private final UserRepo userRepo;

    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserDto registerUser(UserDto userDTO){
        if (userRepo.existsByNicknameOrEmail(userDTO.getNickname(), userDTO.getEmail())){
            throw new UserExistException();
        }

        UserPo userPO = UserPo.builder()
                              .email(userDTO.getEmail())
                              .nickname(userDTO.getNickname())
                              .password(userDTO.getPassword())
                              .phone(userDTO.getPhone())
                              .realName(userDTO.getRealName())
                              .isAdmin(false)
                              .build();

        userRepo.save(userPO);

        userDTO.setId(String.valueOf(userPO.getId()));
        return userDTO;
    }
}
