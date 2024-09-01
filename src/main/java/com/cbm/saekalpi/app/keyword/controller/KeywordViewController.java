package com.cbm.saekalpi.app.keyword.controller;

import com.cbm.saekalpi.app.keyword.service.KeywordViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/keywords")
public class KeywordViewController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final KeywordViewService keywordViewService;

    public KeywordViewController(KeywordViewService keywordViewService) {
        this.keywordViewService = keywordViewService;

    }

}
