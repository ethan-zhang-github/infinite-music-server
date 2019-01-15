package com.just1984.music.web.controller;

import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.web.component.holder.MusicServiceHolder;
import com.just1984.music.web.config.property.MusicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/recommend")
public class RecommendController {

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private MusicServiceHolder musicServiceHolder;

    @GetMapping("/list")
    public List<RecommendVo> list() {
        return musicServiceHolder.getRecommendService(musicProperties.getProfile()).getRecommendList(5);
    }
}
