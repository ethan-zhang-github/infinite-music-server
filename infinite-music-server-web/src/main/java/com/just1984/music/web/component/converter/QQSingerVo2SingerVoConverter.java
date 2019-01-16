package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQSingerVo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum QQSingerVo2SingerVoConverter implements MusicConverter<QQSingerVo, SingerVo> {

    INSTANCE;

    @Override
    public List<SingerVo> convert(Collection<QQSingerVo> src) {
        return src.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

    @Override
    public SingerVo convert(QQSingerVo qqSingerVo) {
        SingerVo vo = new SingerVo();
        vo.setName(qqSingerVo.getName());
        return vo;
    }
}
