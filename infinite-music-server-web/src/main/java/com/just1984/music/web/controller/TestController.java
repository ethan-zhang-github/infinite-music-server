package com.just1984.music.web.controller;

import com.just1984.music.model.enums.MusicExceptionEnum;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }
}
