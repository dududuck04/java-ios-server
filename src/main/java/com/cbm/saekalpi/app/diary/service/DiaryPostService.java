package com.cbm.saekalpi.app.diary.service;

import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.app.diary.dao.DiaryPostDao;
import com.cbm.saekalpi.app.diary.model.DiaryPostDto;
import com.cbm.saekalpi.config.security.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
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
public class DiaryPostService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtService jwtService;

    private final com.cbm.saekalpi.app.diary.dao.DiaryPostDao DiaryPostDao;

    /**
     * DiaryPostService 생성자.
     *
     * @param jwtService   JWT 관련 서비스
     * @param diaryPostDao 다이어리 게시 관련 DAO
     */
    public DiaryPostService(JwtService jwtService, DiaryPostDao diaryPostDao ) {

        DiaryPostDao = diaryPostDao;
        this.jwtService = jwtService;
    }

    /**
     * 다이어리 게시글을 생성하고 데이터베이스에 저장합니다.
     * 사용자의 로그인 상태를 확인하고, 입력된 다이어리 정보를 검증한 후 저장합니다.
     *
     * @param diaryPostDto 다이어리 게시글 데이터 전송 객체
     * @return 저장된 다이어리 정보를 담은 HashMap
     * @throws BaseException 데이터 검증 실패 또는 데이터베이스 처리 오류 발생 시
     */
    @Transactional
    public HashMap<String, Object> postDiary(DiaryPostDto diaryPostDto) throws BaseException {

        /**
         * 로그인 상태 확인
         */
        int userIdx = jwtService.getUserIdx();

        LocalDate date = diaryPostDto.getDate();

        String color = diaryPostDto.getColor();

        String story = diaryPostDto.getStory();

        String emotion = diaryPostDto.getEmotion();

        // 날짜 공백 확인
        if (date == null) {
            throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
        }

        if (color == null) {
            throw new BaseException(POST_DIARYS_EMPTY_COLOR); // "감정 색을 입력해주세요."
        }

        if (emotion == null) {
            throw new BaseException(POST_EMOTION_EMPTY_EMOTION); // "감정 표정을 선택해 주세요"
        }

        // story 글자 수 제한
        if (this.DiaryPostDao.checkStoryLength(story)) {
            throw new BaseException(POST_DIARYS_INVALID_STORY); // "다이어리에 적을 수 있는 글자 수 제한은 300자 입니다."
        }

        // 다이어리 테이블에 다이어리 등록
        try {

            // 다이어리 객체 생성 및 정보 설정
            HashMap<String, Object> diaryPostInfo = new HashMap<String, Object>();

            diaryPostInfo.put("userIdx", userIdx); // 유저 인덱스 추가
            diaryPostInfo.put("date", date);
            diaryPostInfo.put("color", color);
            diaryPostInfo.put("emotion", emotion);

            // DB diary 등록
            this.DiaryPostDao.postDiary(diaryPostInfo);

            // story 처리를 위한 메소드
            List<String> existingStory = null;

            if (story != null) {
                existingStory = this.DiaryPostDao.getAllStoryByDate(date);
                if (existingStory == null) {
                    existingStory = new ArrayList<>();
                }
                existingStory.add(story); // 새로운 story 추가
                diaryPostInfo.put("story", existingStory);
            }

            // DB story 등록
            this.DiaryPostDao.postDiaryStory(existingStory);

            return diaryPostInfo;

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }
}