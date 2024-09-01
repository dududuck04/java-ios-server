package com.cbm.saekalpi.app.keyword.service;

import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.app.keyword.dao.KeywordPostDao;
import com.cbm.saekalpi.app.keyword.model.KeywordPostDto;
import com.cbm.saekalpi.config.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.*;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
public class KeywordViewService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final KeywordPostDao keywordPostDao;

    private final JwtService jwtService;

    public KeywordViewService(KeywordPostDao keywordPostDao, JwtService jwtService) {
        this.jwtService = jwtService;
        this.keywordPostDao = keywordPostDao;
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
    public HashMap<String, Object> getKeywordListByDate(KeywordPostDto keywordPostDto) throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        LocalDate date = keywordPostDto.getDate();

        List<String> keywordList = keywordPostDto.getKeywordList();

        // 날짜 공백 확인
        if (date == null) {
            throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
        }

        // 키워드 리스트 null 체크 및 크기 검증
        if (keywordList == null || keywordList.isEmpty()) {
            throw new BaseException(POST_KEYWORD_EMPTY_KEYWORDLIST); // "keyword를 적어도 하나는 입력해주세요."
        }

        try {

            List<String> registeredKeywordList = this.keywordPostDao.getKeywordListByDate(userIdx, date);

            HashMap<String, Object> keywordListInfo = new HashMap<>();
            keywordListInfo.put("date", date);
            keywordListInfo.put("keywordList", registeredKeywordList);

            return keywordListInfo;

        }catch (Exception exception) {

            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_GET_DIARY); // "다이어리 조회에 실패하였습니다."
        }
    }
}
