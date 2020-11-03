package com.tw.csc.nge.backend.basicbackend.model.dto.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto{

    private String id;

    @NotEmpty(message = "昵称不能为空")
    @Length(max = 32, message = "昵称不能超过32个字符")
    private String nickname;

    @NotEmpty(message = "密码不能为空")
    @Length(max = 32, message = "密码不能超过32个字符")
    private String password;

    @NotEmpty(message = "邮箱不能为空")
    @Length(max = 64, message = "邮箱不能超过64个字符")
    private String email;

    @Length(max = 20, message = "真实姓名不能超过20个字符")
    private String realName;

    @Length(max = 20, message = "电话号码不能超过20个字符")
    private String phone;

}
