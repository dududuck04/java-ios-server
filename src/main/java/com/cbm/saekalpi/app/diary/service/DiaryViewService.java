package com.cbm.saekalpi.app.diary.service;

import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.app.diary.dao.DiaryPostDao;
import com.cbm.saekalpi.config.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.*;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
public class DiaryViewService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final DiaryPostDao diaryPostDao;

    private final JwtService jwtService;

    public DiaryViewService(DiaryPostDao diaryPostDao, JwtService jwtService) {
        this.jwtService = jwtService;
        this.diaryPostDao = diaryPostDao;
    }

    /**
     * 로그인 상태 설정 클래스
     *
     * @param
     * @return
     * @exception
     */
    /* 회원가입 인증 - userJoinCheck() */
    @Transactional
    public HashMap<String, Object> getDiaryByDate(LocalDate date) throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        // 날짜 공백 확인
        if (date == null) {
            throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
        }

        try {

            // 조회하고 싶은 날짜에 다이어리가 존재하는 지 확인
            if (this.diaryPostDao.checkDiaryEmpty(date)) {
                throw new BaseException(GET_DIARY_FAIL); // "특정 날짜에 생성된 다이어리가 없습니다."
            }

            return this.diaryPostDao.getDiaryByDate(date, userIdx); // 조회 성공시 다이어리 정보 반환

        }catch (Exception exception) {

            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_GET_DIARY); // "다이어리 조회에 실패하였습니다."
        }
    }

    @Transactional
    public HashMap<String, Object> getDiaryColorByMonth(LocalDate date) throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        // 날짜 공백 확인
        if (date == null) {
            throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
        }

        try {

            return this.diaryPostDao.getDiaryByDate(date, userIdx); // 조회 성공시 다이어리 정보 반환

        }catch (Exception exception) {

            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_GET_DIARY); // "다이어리 조회에 실패하였습니다."
        }
    }
}
