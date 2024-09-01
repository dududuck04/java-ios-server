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
import java.util.List;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.*;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
public class KeywordPostService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtService jwtService;

    private final KeywordPostDao keywordPostDao;

    /**
     * DiaryPostService 생성자.
     *
     * @param jwtService   JWT 관련 서비스
     * @param keyWordPostDao 다이어리 게시 관련 DAO
     */
    public KeywordPostService(JwtService jwtService, KeywordPostDao keyWordPostDao, KeywordPostDao keywordPostDao) {
        this.keywordPostDao = keywordPostDao;
        this.jwtService = jwtService;
    }

    /**
     * 다이어리 게시글을 생성하고 데이터베이스에 저장합니다.
     * 사용자의 로그인 상태를 확인하고, 입력된 다이어리 정보를 검증한 후 저장합니다.
     *
     * @param keyWordPostDto 다이어리 게시글 데이터 전송 객체
     * @return 저장된 다이어리 정보를 담은 HashMap
     * @throws BaseException 데이터 검증 실패 또는 데이터베이스 처리 오류 발생 시
     */
    @Transactional
    public void postKeyWordByDate(KeywordPostDto keyWordPostDto) throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        LocalDate date = keyWordPostDto.getDate();

        List<String> keywordList = keyWordPostDto.getKeywordList();

        // 날짜 공백 확인
        if (date == null) {
            throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
        }

        // 키워드 리스트 null 체크 및 크기 검증
        if (keywordList == null || keywordList.isEmpty()) {
            throw new BaseException(POST_KEYWORD_EMPTY_KEYWORDLIST); // "keyword를 적어도 하나는 입력해주세요."
        }

        if (keywordList.size() > 3) {
            throw new BaseException(POST_KEYWORD_EXCEED_LIMIT); // "키워드는 최대 3개까지 등록 가능합니다."
        }

        // 각 키워드의 형식 및 내용 검증
        for (String keyword : keywordList) {
            if (keyword.length() > 10) { // MAX_KEYWORD_LENGTH는 상수로 정의
                throw new BaseException(POST_KEYWORD_INVALID_LENGTH); // "키워드 길이는 10자를 초과할 수 없습니다."
            }
        }

        // 키워드 테이블에 키워드 등록
        try {

            // 다이어리 객체 생성 및 정보 설정
            this.keywordPostDao.postKeywordListByDate(userIdx, date, keywordList);

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }

    @Transactional
    public void postDefaulKeywordList(int userIdx) throws BaseException {

        try {
            keywordPostDao.postDefaultKeywordSetting(userIdx);

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }
}