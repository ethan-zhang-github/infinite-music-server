package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQSingerItemVo;

public enum QQSingerItemVo2SingerVoConverter implements MusicConverter<QQSingerItemVo, SingerVo> {

    INSTANCE;

    @Override
    public SingerVo convert(QQSingerItemVo qqSingerItemVo) {
        return SingerVo.builder()
                .id(Long.valueOf(qqSingerItemVo.getFsinger_id()))
                .mid(qqSingerItemVo.getFsinger_mid())
                .name(qqSingerItemVo.getFsinger_name())
                .index(qqSingerItemVo.getFindex()).build();
    }
}
