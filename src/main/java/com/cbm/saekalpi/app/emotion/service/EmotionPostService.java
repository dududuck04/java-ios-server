package com.cbm.saekalpi.app.emotion.service;

import com.cbm.saekalpi.app.emotion.dao.EmotionPostDao;
import com.cbm.saekalpi.config.utils.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.DATABASE_ERROR_CREATE_DIARY;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
public class EmotionPostService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    final EmotionPostDao emotionPostDao;

    /**
     * DiaryPostService 생성자.
     */
    public EmotionPostService(EmotionPostDao emotionPostDao) {

        this.emotionPostDao = emotionPostDao;
    }

    /**
     * 다이어리 게시글을 생성하고 데이터베이스에 저장합니다.
     * 사용자의 로그인 상태를 확인하고, 입력된 다이어리 정보를 검증한 후 저장합니다.
     *
     * @param colorPostDto 다이어리 게시글 데이터 전송 객체
     * @return 저장된 다이어리 정보를 담은 HashMap
     * @throws BaseException 데이터 검증 실패 또는 데이터베이스 처리 오류 발생 시
     */
    @Transactional
    public void postDefaulEmotionList(int userIdx) throws BaseException {

        try {
            emotionPostDao.postDefaultEmotionSetting(userIdx);

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }
}