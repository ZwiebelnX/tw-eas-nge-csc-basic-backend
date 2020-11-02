package com.tw.csc.nge.backend.basicbackend.repository;

import com.tw.csc.nge.backend.basicbackend.model.po.UserPo;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepo extends PagingAndSortingRepository<UserPo, Long>{

    boolean existsByNicknameOrEmail(String nickname, String email);

    UserPo getByNicknameOrEmail(String nickName, String email);
}
