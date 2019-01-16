package com.just1984.music.web.controller;

import com.just1984.music.model.vo.DiscVo;
import com.just1984.music.model.vo.ResultVo;
import com.just1984.music.web.component.holder.MusicServiceHolder;
import com.just1984.music.web.config.property.MusicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/disc")
public class DiscController {

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private MusicServiceHolder musicServiceHolder;

    @GetMapping("/list")
    public ResultVo<List<DiscVo>> list() {
        List<DiscVo> discList = musicServiceHolder.getDiscService(musicProperties.getProfile()).getDiscList(30);
        return ResultVo.success(discList);
    }
}
