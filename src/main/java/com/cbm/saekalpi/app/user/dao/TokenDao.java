package com.cbm.saekalpi.app.user.dao;

import com.cbm.saekalpi.config.security.CustomUserDetails;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;


/**
 * 모든 사원 목록을 조회한다.
 * 
 * @param authCode
 * @param userReq
 * 
 * @return 사원 목록
 */
@Mapper
public interface TokenDao {

	boolean updateRefreshToken(int userIdx, String refreshToken);

	boolean deleteRefreshToken();

	CustomUserDetails getUserByRefreshToken(String refreshToken);

}

