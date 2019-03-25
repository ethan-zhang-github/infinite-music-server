package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.SongVo;
import com.just1984.music.model.vo.qq.QQTopSongVo;

public enum QQTopSongVo2SongVoConverter implements MusicConverter<QQTopSongVo, SongVo> {

    INSTANCE;

    @Override
    public SongVo convert(QQTopSongVo qqTopSongVo) {
        SongVo vo = new SongVo();
        vo.setName(qqTopSongVo.getSongname());
        vo.setSinger(SingerVo.builder().name(qqTopSongVo.getSingername()).build());
        return vo;
    }
}
