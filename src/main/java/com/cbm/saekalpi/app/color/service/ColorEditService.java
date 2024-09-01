package com.cbm.saekalpi.app.color.service;

import com.cbm.saekalpi.app.color.dao.ColorPostDao;
import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.app.color.model.ColorPostDto;
import com.cbm.saekalpi.config.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static com.cbm.saekalpi.config.utils.BaseResponseStatus.*;

/**
 * 유저 기본 인증 정보 CUD 를 위한 Service
 *
 * @author 김경민
 * @version 1.0, 유저 회원가입, 인증, 로그인, 로그아웃, 탈퇴
 */
@Service
@RequiredArgsConstructor

public class ColorEditService {

    final Logger logger = LoggerFactory.getLogger(this.getClass());
    final ColorPostDao colorPostDao;

    final JwtService jwtService;

    /**
     * 다이어리 게시글을 생성하고 데이터베이스에 저장합니다.
     * 사용자의 로그인 상태를 확인하고, 입력된 다이어리 정보를 검증한 후 저장합니다.
     *
     * @param colorPostDto 다이어리 게시글 데이터 전송 객체
     * @return 저장된 다이어리 정보를 담은 HashMap
     * @throws BaseException 데이터 검증 실패 또는 데이터베이스 처리 오류 발생 시
     */
    @Transactional
    public void patchColorByDate(ColorPostDto colorPostDto) throws BaseException {

        try {

            int userIdx = jwtService.getUserIdx(); // JWT에서 userIdx 추출

            LocalDate date = colorPostDto.getDate(); // 날짜 추출

            // 날짜 공백 확인
            if (date == null) {
                throw new BaseException(POST_DIARYS_EMPTY_DATE); // "날짜를 입력해주세요."
            }

            colorPostDao.patchMyColorByDate(userIdx, date);

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }

    @Transactional
    public void patchMyColorSetting(ColorPostDto colorPostDto) throws BaseException {

        try {

            int userIdx = jwtService.getUserIdx(); // JWT에서 userIdx 추출

            String color = colorPostDto.getColor();

            String colorName = colorPostDto.getColorName();

            // 날짜 공백 확인
            if (color == null) {
                throw new BaseException(POST_DIARYS_EMPTY_COLOR); // "감정 색을 입력해주세요."
            }

            if (colorName == null) {
                throw new BaseException(POST_DIARYS_EMPTY_COLORNAME); // "감정 색 이름을 입력해주세요."
            }
            colorPostDao.patchMyColorSetting(userIdx, color, colorName);

        } catch (Exception exception) {
            logger.error("DB 처리 오류", exception);
            throw new BaseException(DATABASE_ERROR_CREATE_DIARY); // "다이어리를 DB에 등록하지 못하였습니다."
        }
    }
}