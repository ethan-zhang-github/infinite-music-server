package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.model.vo.qq.QQSlider;
import org.springframework.beans.BeanUtils;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public enum QQSlider2RecommendVoConverter implements MusicConverter<QQSlider, RecommendVo> {

    INSTANCE;

    @Override
    public List<RecommendVo> convert(Collection<QQSlider> src) {
        return src.stream().map(item -> convert(item)).collect(Collectors.toList());
    }

    @Override
    public RecommendVo convert(QQSlider qqSlider) {
        RecommendVo vo = new RecommendVo();
        BeanUtils.copyProperties(qqSlider, vo);
        return vo;
    }
}
