package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQSingerVo;

public enum QQSingerVo2SingerVoConverter implements MusicConverter<QQSingerVo, SingerVo> {

    INSTANCE;

    @Override
    public SingerVo convert(QQSingerVo qqSingerVo) {
        return SingerVo.builder().name(qqSingerVo.getName()).build();
    }
}
