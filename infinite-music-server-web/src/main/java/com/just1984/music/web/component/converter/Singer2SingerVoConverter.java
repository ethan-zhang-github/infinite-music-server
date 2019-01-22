package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.persistence.entity.Singer;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum Singer2SingerVoConverter implements MusicConverter<Singer, SingerVo> {

    INSTANCE;

    @Override
    public List<SingerVo> convert(Collection<Singer> src) {
        return src.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

    @Override
    public SingerVo convert(Singer singer) {
        SingerVo vo = SingerVo.builder().index(singer.getAlphabet()).build();
        BeanUtils.copyProperties(singer, vo);
        return vo;
    }
}
