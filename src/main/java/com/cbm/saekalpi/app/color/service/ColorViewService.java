package com.cbm.saekalpi.app.color.service;

import com.cbm.saekalpi.app.color.dao.ColorPostDao;
import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.config.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.*;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
@RequiredArgsConstructor

public class ColorViewService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final ColorPostDao colorPostDao;

    private final JwtService jwtService;

    /**
     * 현재 로그인한 사용자의 색상 설정을 조회합니다.
     *
     * @return 현재 사용자의 색상 설정을 담은 HashMap.
     * @throws BaseException 로그인 확인 또는 데이터베이스 처리 중 오류 발생 시.
     */
    @Transactional
    public HashMap<String, Object> getMyColor() throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        try {

            return colorPostDao.getMyColorSetting(userIdx); // 사용자 ID를 기반으로 색상 설정을 조회합니다.

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_GET_DIARY); // "다이어리 조회에 실패하였습니다."
        }
    }
}
