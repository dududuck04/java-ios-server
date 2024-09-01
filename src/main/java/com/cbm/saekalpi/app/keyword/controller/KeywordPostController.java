package com.cbm.saekalpi.app.keyword.controller;

import com.cbm.saekalpi.app.keyword.service.KeywordPostService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/keywords")
public class KeywordPostController {

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KeywordPostService keyWordPostService;


    /**
     * 다이어리 생성 처리 생성자
     */
    public KeywordPostController(KeywordPostService keyWordPostService) {
        this.keyWordPostService = keyWordPostService;
    }

}
