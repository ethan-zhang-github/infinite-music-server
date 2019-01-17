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
        SingerVo vo = new SingerVo();
        vo.setId(Long.valueOf(qqSingerItemVo.getFsinger_id()));
        vo.setName(qqSingerItemVo.getFsinger_name());
        vo.setOtherName(qqSingerItemVo.getFsinger_name());
        vo.setIndex(qqSingerItemVo.getFindex());
        return vo;
    }
}
