package com.tw.csc.nge.backend.basicbackend.model.po;

import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "user")
public class UserPO extends BasicPO {

    @Column(nullable = false, length = 32)
    private String nickname;

    @Column(nullable = false, length = 32)
    private String password;

    @Column(nullable = false, length = 64)
    private String email;

    @Column(length = 20)
    private String realName;

    @Column(length = 20)
    private String phone;
}
