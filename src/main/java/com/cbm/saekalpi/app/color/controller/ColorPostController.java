package com.cbm.saekalpi.app.color.controller;

import com.cbm.saekalpi.app.color.model.ColorPostDto;
import com.cbm.saekalpi.app.color.service.ColorPostService;
import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.config.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor

@RequestMapping("/colors")
public class ColorPostController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ColorPostService colorPostService;

    /**
     * 회원 탈퇴
     *
     * @return BaseResponse<String>
     */
    @ResponseBody
    @PostMapping("date")
    public BaseResponse<String> postDiary(@RequestBody ColorPostDto colorPostDto) {

        try {
            /**
             * 키워드 등록
             */

            colorPostService.postColorByDate(colorPostDto);

            return new BaseResponse<>("키워드가 성공적으로 등록되었습니다.");

        } catch (BaseException exception) {

            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
