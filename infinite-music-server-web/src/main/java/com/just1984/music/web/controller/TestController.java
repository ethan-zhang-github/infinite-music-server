package com.just1984.music.web.controller;

import com.just1984.music.web.config.property.MusicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class TestController {

    @Autowired
    private MusicProperties musicProperties;

    @GetMapping("/test")
    public String test() {
        log.info(musicProperties.getQq().getRecommendListUrl());
        return "Hello World";
    }

}
