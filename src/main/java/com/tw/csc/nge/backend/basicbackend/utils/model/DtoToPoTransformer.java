package com.tw.csc.nge.backend.basicbackend.utils.model;

import com.tw.csc.nge.backend.basicbackend.model.dto.user.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;

public class DtoToPoTransformer{

    public static UserPo userDtoToUserPo(UserDto userDto){
        return UserPo.builder()
                     .email(userDto.getEmail())
                     .nickname(userDto.getNickname())
                     .password(userDto.getPassword())
                     .phone(userDto.getPhone())
                     .realName(userDto.getRealName())
                     .isAdmin(false)
                     .build();
    }
}
