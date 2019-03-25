package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.DiscVo;
import com.just1984.music.model.vo.qq.QQDiscItemVo;

public enum QQDiscItemVo2DiscVoConverter implements MusicConverter<QQDiscItemVo, DiscVo> {

    INSTANCE;

    @Override
    public DiscVo convert(QQDiscItemVo qqDiscItemVo) {
        DiscVo vo = new DiscVo();
        vo.setId(Long.valueOf(qqDiscItemVo.getDissid()));
        vo.setName(qqDiscItemVo.getDissname());
        vo.setImgUrl(qqDiscItemVo.getImgurl());
        vo.setSinger(QQSingerVo2SingerVoConverter.INSTANCE.convert(qqDiscItemVo.getCreator()));
        return vo;
    }
}
