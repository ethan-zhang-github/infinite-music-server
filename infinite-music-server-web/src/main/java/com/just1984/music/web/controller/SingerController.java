package com.just1984.music.web.controller;

import com.just1984.music.model.vo.ResultVo;
import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.web.component.holder.MusicServiceHolder;
import com.just1984.music.web.config.property.MusicProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/singer")
public class SingerController {

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private MusicServiceHolder musicServiceHolder;

    @GetMapping("/list")
    public ResultVo<List<SingerVo>> list() {
        List<SingerVo> singerList = musicServiceHolder.getSingerService(musicProperties.getProfile()).getSingerList(100);
        return ResultVo.success(singerList);
    }

    @GetMapping("/detail/{id}")
    public ResultVo<SingerVo> getById(@PathVariable Long id) {
        SingerVo singerVo = musicServiceHolder.getSingerService(musicProperties.getProfile()).getById(id);
        return ResultVo.success(singerVo);
    }
}
