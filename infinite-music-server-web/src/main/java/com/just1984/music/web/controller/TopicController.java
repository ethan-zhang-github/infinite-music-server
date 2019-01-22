package com.just1984.music.web.controller;

import com.just1984.music.model.vo.ResultVo;
import com.just1984.music.model.vo.TopicVo;
import com.just1984.music.web.component.holder.MusicServiceHolder;
import com.just1984.music.web.config.property.MusicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/topic")
public class TopicController {

    @Autowired
    private MusicServiceHolder musicServiceHolder;

    @Autowired
    private MusicProperties musicProperties;

    @GetMapping("/list")
    public ResultVo<List<TopicVo>> list() {
        List<TopicVo> topicList = musicServiceHolder.getTopicService(musicProperties.getProfile()).getTopicList(14);
        return ResultVo.success(topicList);
    }
}
