package com.just1984.music.web.service;

import com.google.common.collect.Maps;
import com.just1984.music.model.constant.QQConstants;
import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.model.vo.qq.QQRecommendVo;
import com.just1984.music.model.vo.qq.QQResponseVo;
import com.just1984.music.web.component.converter.QQSlider2RecommendVoConverter;
import com.just1984.music.web.config.property.MusicProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service("qQRecommendService")
@Slf4j
public class QQRecommendService implements RecommendService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicProperties musicProperties;

    @Override
    public List<RecommendVo> getRecommendList(int size) {
        Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("platform", "h5");
        params.put("uin", 0);
        params.put("needNewCode", 1);
        ResponseEntity<QQResponseVo<QQRecommendVo>> res = restTemplate.exchange(musicProperties.getQq().getRecommendListUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<QQResponseVo<QQRecommendVo>>() {}, params);
        return QQSlider2RecommendVoConverter.INSTANCE.convert(res.getBody().getData().getSlider());
    }
}
