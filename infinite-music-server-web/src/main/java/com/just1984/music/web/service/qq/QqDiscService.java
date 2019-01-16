package com.just1984.music.web.service.qq;

import com.google.common.collect.Maps;
import com.just1984.music.model.constant.QQConstants;
import com.just1984.music.model.vo.DiscVo;
import com.just1984.music.model.vo.qq.QQDiscVo;
import com.just1984.music.model.vo.qq.QQResponseVo;
import com.just1984.music.web.component.converter.QQDiscItemVo2DiscVoConverter;
import com.just1984.music.web.config.property.MusicProperties;
import com.just1984.music.web.service.DiscService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service("qqDiscService")
public class QqDiscService implements DiscService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicProperties musicProperties;

    @Override
    public List<DiscVo> getDiscList(int size) {
        Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("platform", "yqq");
        params.put("hostUin", 0);
        params.put("sin", 0);
        params.put("ein", 29);
        params.put("sortId", 5);
        params.put("needNewCode", 0);
        params.put("categoryId", 10000000);
        params.put("rnd", Math.random());
        params.put("format", "json");
        //params.put("referer", "https://c.y.qq.com/");
        //params.put("host", "c.y.qq.com");
        HttpHeaders headers = new HttpHeaders();
        headers.add("referer", "https://c.y.qq.com/");
        headers.add("host", "c.y.qq.com");
        ResponseEntity<QQResponseVo<QQDiscVo>> res = restTemplate.exchange(musicProperties.getQq().getDiscListUrl(),
                HttpMethod.GET, new HttpEntity<>(headers), new ParameterizedTypeReference<QQResponseVo<QQDiscVo>>() {}, params);
        return QQDiscItemVo2DiscVoConverter.INSTANCE.convert(res.getBody().getData().getList());
    }
}
