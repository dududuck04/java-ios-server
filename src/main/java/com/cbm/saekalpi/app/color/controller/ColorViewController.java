package com.cbm.saekalpi.app.color.controller;

import com.cbm.saekalpi.app.color.service.ColorViewService;
import com.cbm.saekalpi.config.utils.BaseException;
import com.cbm.saekalpi.config.utils.BaseResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor

@RequestMapping("/colors")
public class ColorViewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final ColorViewService colorViewService;

    /**
     * 사용자의 기본 16가지 색상 정보를 조회합니다.
     *
     * @return 조회된 색상 정보를 담은 BaseResponse 객체.
     * @throws BaseException 색상 조회 과정에서 발생한 예외.
     */
    @GetMapping("")
    public BaseResponse<HashMap<String, Object>> getMyColor() {

        try {

            HashMap<String, Object> myColorInfo = colorViewService.getMyColor();

            return new BaseResponse<>(myColorInfo);

        }

        catch (BaseException exception) {

            logger.error("BaseException", exception);
            return new BaseResponse<>((exception.getStatus()));
        }
    }

}
