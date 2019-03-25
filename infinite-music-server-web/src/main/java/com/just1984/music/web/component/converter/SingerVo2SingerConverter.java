package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.persistence.entity.Singer;

public enum SingerVo2SingerConverter implements MusicConverter<SingerVo, Singer> {

    INSTANCE;

    @Override
    public Singer convert(SingerVo singerVo) {
        Singer singer = new Singer();
        singer.setName(singerVo.getName());
        singer.setOtherName(singerVo.getOtherName());
        singer.setAlphabet(singerVo.getIndex());
        singer.setMid(singerVo.getMid());
        return singer;
    }
}
