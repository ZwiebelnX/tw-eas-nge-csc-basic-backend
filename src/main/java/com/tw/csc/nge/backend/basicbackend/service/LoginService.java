package com.tw.csc.nge.backend.basicbackend.service;

import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessException;
import com.tw.csc.nge.backend.basicbackend.common.exception.BusinessExceptionType;
import com.tw.csc.nge.backend.basicbackend.model.dto.LoginDto;
import com.tw.csc.nge.backend.basicbackend.model.dto.UserDto;
import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import com.tw.csc.nge.backend.basicbackend.repository.UserRepo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class LoginService{

    private final UserRepo userRepo;

    private final Map<Long, HttpSession> userMap = new ConcurrentHashMap<>();

    public LoginService(UserRepo userRepo){
        this.userRepo = userRepo;
    }

    public UserDto userLogin(LoginDto loginDto, HttpSession httpSession){
        UserPo userPo = userRepo.getByNicknameOrEmail(loginDto.getLoginName(), loginDto.getLoginName());
        if(userPo == null || !userPo.getPassword().equals(loginDto.getPassword())){
            throw new BusinessException(BusinessExceptionType.LOGIN_INFO_ILLEGAL);
        }

        userMap.compute(userPo.getId(), (key, value) -> {
            if(value != null){
                value.invalidate();
            }
            return httpSession;
        });
        UserDto userDto = UserDto.builder()
                                 .id(String.valueOf(userPo.getId()))
                                 .email(userPo.getEmail())
                                 .nickname(userPo.getNickname())
                                 .phone(userPo.getPhone())
                                 .realName(userPo.getRealName())
                                 .build();
        httpSession.setAttribute("userInfo", userDto);

        return userDto;
    }

    public void userLogout(HttpSession httpSession){
        UserDto userDto = (UserDto)httpSession.getAttribute("userInfo");
        if(userDto != null){
            HttpSession oldHttpSession = userMap.remove(Long.parseLong(userDto.getId()));
            oldHttpSession.invalidate();
        }
    }
}
