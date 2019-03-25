package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.persistence.entity.Singer;
import org.springframework.beans.BeanUtils;

public enum Singer2SingerVoConverter implements MusicConverter<Singer, SingerVo> {

    INSTANCE;

    @Override
    public SingerVo convert(Singer singer) {
        SingerVo vo = SingerVo.builder().index(singer.getAlphabet()).build();
        BeanUtils.copyProperties(singer, vo);
        return vo;
    }
}
