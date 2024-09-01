package com.cbm.saekalpi.app.diary.controller;

import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.config.utils.BaseResponse;
import com.cbm.saekalpi.app.diary.service.DiaryViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;

@RestController
@RequestMapping("/diarys")
public class DiaryViewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DiaryViewService diaryViewService;

    public DiaryViewController(DiaryViewService diaryViewService) {
        this.diaryViewService = diaryViewService;

    }
    /**
     * 특정 날짜의 다이어리 내용을 조회하는 컨트롤러 메소드.
     * 클라이언트로부터 받은 DiaryPostDto 객체를 사용하여 해당 날짜의 다이어리를 조회합니다.
     *
     * @return 조회된 다이어리 정보를 담은 BaseResponse 객체.
     * @throws BaseException 다이어리 조회 과정에서 발생한 예외.
     */
    @GetMapping("/{date}")
    public BaseResponse<HashMap<String, Object>> getDiaryByDate(@PathVariable("date") LocalDate date) {

        try {
            HashMap<String, Object> diaryDateInfo = diaryViewService.getDiaryByDate(date);

            return new BaseResponse<>(diaryDateInfo);
        }

        catch (BaseException exception) {

            logger.error("BaseException", exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    @GetMapping("/{date}/color")
    public BaseResponse<HashMap<String, Object>> getDiaryColorByMonth(@PathVariable("date") LocalDate date) {

        try {
            diaryViewService.getDiaryColorByMonth(date);

            HashMap<String, Object> diaryMonthInfo = diaryViewService.getDiaryColorByMonth(date);

            return new BaseResponse<>(diaryMonthInfo);

        }

        catch (BaseException exception) {

            logger.error("BaseException", exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
