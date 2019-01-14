package com.just1984.music.web.controller;

import com.just1984.music.model.vo.RecommendVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @GetMapping("/list")
    public List<RecommendVo> list() {
        return null;
    }
}
