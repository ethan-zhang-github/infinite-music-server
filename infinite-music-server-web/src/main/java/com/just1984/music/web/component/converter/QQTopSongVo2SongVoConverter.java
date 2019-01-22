package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.SongVo;
import com.just1984.music.model.vo.qq.QQTopSongVo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum QQTopSongVo2SongVoConverter implements MusicConverter<QQTopSongVo, SongVo> {

    INSTANCE;

    @Override
    public List<SongVo> convert(Collection<QQTopSongVo> src) {
        return src.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

    @Override
    public SongVo convert(QQTopSongVo qqTopSongVo) {
        SongVo vo = new SongVo();
        vo.setName(qqTopSongVo.getSongname());
        vo.setSinger(SingerVo.builder().name(qqTopSongVo.getSingername()).build());
        return vo;
    }
}
