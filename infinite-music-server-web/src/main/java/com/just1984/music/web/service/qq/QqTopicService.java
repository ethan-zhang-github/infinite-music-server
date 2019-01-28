package com.just1984.music.web.service.qq;

import com.google.common.collect.Maps;
import com.just1984.music.core.util.JsonMapper;
import com.just1984.music.model.constant.QQConstants;
import com.just1984.music.model.vo.SongVo;
import com.just1984.music.model.vo.TopicVo;
import com.just1984.music.model.vo.qq.QQResponseVo;
import com.just1984.music.model.vo.qq.QQTopListVo;
import com.just1984.music.model.vo.qq.QQTopSongListVo;
import com.just1984.music.web.component.concurrency.MusicConcurrency;
import com.just1984.music.web.component.converter.QQTopItemVo2TopicVoConverter;
import com.just1984.music.web.config.property.MusicProperties;
import com.just1984.music.web.service.TopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Slf4j
@Service("qqTopicService")
public class QqTopicService implements TopicService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private MusicConcurrency musicConcurrency;

    @Override
    public List<TopicVo> getTopicList(int size) {
        Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("uin", 0);
        params.put("needNewCode", 1);
        params.put("platform", "h5");
        ResponseEntity<QQResponseVo<QQTopListVo>> res = restTemplate.exchange(musicProperties.getQq().getTopListUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<QQResponseVo<QQTopListVo>>() {}, params);
        List<TopicVo> topicVoList = QQTopItemVo2TopicVoConverter.INSTANCE.convert(res.getBody().getData().getTopList());
        musicConcurrency.saveTopicList(topicVoList);
        return topicVoList;
    }

    @Override
    public List<SongVo> getSongListByTopicId(String id, int size) {
        Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("topid", Long.valueOf(id));
        params.put("needNewCode", 1);
        params.put("uin", 0);
        params.put("tpl", 3);
        params.put("page", "detail");
        params.put("type", "top");
        params.put("platform", "h5");
        ResponseEntity<QQTopSongListVo> res = restTemplate.exchange(musicProperties.getQq().getTopMusicListUrl(),
                HttpMethod.GET, null, new ParameterizedTypeReference<QQTopSongListVo>() {}, params);
        log.info(JsonMapper.obj2String(res));
        return null;
    }

}
