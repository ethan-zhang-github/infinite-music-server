package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQSingerItemVo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum QQSingerItemVo2SingerVoConverter implements MusicConverter<QQSingerItemVo, SingerVo> {

    INSTANCE;

    @Override
    public List<SingerVo> convert(Collection<QQSingerItemVo> src) {
        return src.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

    @Override
    public SingerVo convert(QQSingerItemVo qqSingerItemVo) {
        return SingerVo.builder()
                .id(Long.valueOf(qqSingerItemVo.getFsinger_id()))
                .mid(qqSingerItemVo.getFsinger_mid())
                .name(qqSingerItemVo.getFsinger_name())
                .index(qqSingerItemVo.getFindex()).build();
    }
}
