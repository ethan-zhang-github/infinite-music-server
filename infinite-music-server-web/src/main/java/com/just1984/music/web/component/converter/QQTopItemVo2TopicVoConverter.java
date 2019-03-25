package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.TopicVo;
import com.just1984.music.model.vo.qq.QQTopItemVo;

public enum QQTopItemVo2TopicVoConverter implements MusicConverter<QQTopItemVo, TopicVo> {

    INSTANCE;

    @Override
    public TopicVo convert(QQTopItemVo qqTopItemVo) {
        TopicVo vo = new TopicVo();
        vo.setId(qqTopItemVo.getId());
        vo.setName(qqTopItemVo.getTopTitle());
        vo.setPicUrl(qqTopItemVo.getPicUrl());
        vo.setSongList(QQTopSongVo2SongVoConverter.INSTANCE.convert(qqTopItemVo.getSongList()));
        return vo;
    }
}
