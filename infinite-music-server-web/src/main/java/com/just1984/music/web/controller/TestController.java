package com.just1984.music.web.controller;

import com.just1984.music.persistence.entity.Keyword;
import com.just1984.music.web.service.KeywordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    private KeywordService keywordService;

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

    @GetMapping("/keyword")
    public Keyword keyword() {
        Keyword keyword = new Keyword();
        keyword.setCode("123");
        keyword.setName("测试");
        return keywordService.save(keyword);
    }
}
