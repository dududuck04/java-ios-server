package com.cbm.saekalpi.app.diary.controller;

import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.config.utils.BaseResponse;
import com.cbm.saekalpi.app.diary.model.DiaryPostDto;
import com.cbm.saekalpi.app.diary.service.DiaryPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/diarys")
public class DiaryPostController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final DiaryPostService diaryPostService;

    /**
     * 다이어리 생성 처리 생성자
     */
    public DiaryPostController(DiaryPostService diaryPostService) {
        this.diaryPostService = diaryPostService;
    }

    /**
     * 다이어리 등록
     *
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("")
    public BaseResponse<HashMap<String, Object>> postDiary(@RequestBody DiaryPostDto diaryPostDto) {

        try {
            /**
             * 다이어리 등록
             */
            HashMap<String, Object> diaryPostInfo = new HashMap<String, Object>();

            diaryPostInfo = diaryPostService.postDiary(diaryPostDto);

            return new BaseResponse<>(diaryPostInfo);

        } catch (BaseException exception) {

            return new BaseResponse<>((exception.getStatus()));
        }
    }
}
