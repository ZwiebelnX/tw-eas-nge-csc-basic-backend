package com.tw.csc.nge.backend.basicbackend.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginDTO {

    @NotEmpty(message = "登录名不能为空")
    @Length(min = 1, max = 64)
    private String loginName;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 32)
    private String password;

}
