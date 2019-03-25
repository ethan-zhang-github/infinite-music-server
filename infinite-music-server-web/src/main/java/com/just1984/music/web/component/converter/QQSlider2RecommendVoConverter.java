package com.just1984.music.web.component.converter;

import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.model.vo.qq.QQSlider;
import org.springframework.beans.BeanUtils;

public enum QQSlider2RecommendVoConverter implements MusicConverter<QQSlider, RecommendVo> {

    INSTANCE;

    @Override
    public RecommendVo convert(QQSlider qqSlider) {
        RecommendVo vo = new RecommendVo();
        BeanUtils.copyProperties(qqSlider, vo);
        return vo;
    }
}
