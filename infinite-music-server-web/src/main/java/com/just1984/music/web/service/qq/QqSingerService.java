package com.just1984.music.web.service.qq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.just1984.music.core.util.JsonMapper;
import com.just1984.music.model.constant.QQConstants;
import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQResponseVo;
import com.just1984.music.model.vo.qq.QQSingerDetailResponseVo;
import com.just1984.music.model.vo.qq.QQSingerListVo;
import com.just1984.music.model.vo.qq.QQSongListVo;
import com.just1984.music.web.component.concurrency.MusicConcurrency;
import com.just1984.music.web.component.converter.QQSingerItemVo2SingerVoConverter;
import com.just1984.music.web.config.property.MusicProperties;
import com.just1984.music.web.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Slf4j
@Service("qqSingerService")
public class QqSingerService implements SingerService {

    @Autowired
    private MusicProperties musicProperties;

    @Autowired
    private MusicConcurrency musicConcurrency;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public List<SingerVo> getSingerList(int size) {
        /*Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("channel", "singer");
        params.put("page", "list");
        params.put("key", "all_all_all");
        params.put("pagesize", 100);
        params.put("pagenum", 1);
        params.put("hostUin", 0);
        params.put("needNewCode", 0);
        params.put("platform", "yqq");
        ResponseEntity<QQResponseVo<QQSingerListVo>> res = restTemplate.exchange(musicProperties.getQq().getSingerListUrl(), HttpMethod.GET, null,
                new ParameterizedTypeReference<QQResponseVo<QQSingerListVo>>() {}, params);*/
        Resource resource = new ClassPathResource("static/singer-list.json");
        QQResponseVo<QQSingerListVo> res = new QQResponseVo<>();
        try {
            res = objectMapper.readValue(resource.getFile(), new TypeReference<QQResponseVo<QQSingerListVo>>() {});
        } catch (IOException e) {
            log.error("json parse error", e);
        }
        List<SingerVo> singerVoList = QQSingerItemVo2SingerVoConverter.INSTANCE.convert(res.getData().getList());
        musicConcurrency.saveSingerList(singerVoList);
        return singerVoList;
    }

    @Override
    public SingerVo getById(String id) {
        Map<String, Object> params = Maps.newHashMap();
        params.putAll(QQConstants.commonParams);
        params.put("hostUin", 0);
        params.put("needNewCode", 0);
        params.put("platform", "yqq");
        params.put("order", "listen");
        params.put("begin", 0);
        params.put("num", 80);
        params.put("songstatus", 1);
        params.put("singermid", id);
        HttpHeaders headers = new HttpHeaders();
        headers.add("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36");
        headers.add("Accept", "*/*");
        //headers.add("Accept-Encoding", "gzip, deflate, br");
        headers.add("Accept-Language", "zh-CN,zh;q=0.9");
        headers.add("Cookie", "pgv_pvi=774567936");
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        /*ResponseEntity<QQResponseVo<QQSongListVo>> res = restTemplate.exchange(musicProperties.getQq().getSingerDetailUrl(),
                HttpMethod.GET, requestEntity, new ParameterizedTypeReference<QQResponseVo<QQSongListVo>>() {}, params);
        log.info(JsonMapper.obj2String(res.getBody()));*/

        QQSingerDetailResponseVo res = restTemplate.getForObject("https://c.y.qq.com/v8/fcg-bin/fcg_v8_singer_track_cp.fcg?hostUin=0&needNewCode=0&platform=yqq&order=listen&begin=0&num=80&songstatus=1&singermid=002J4UUk29y8BY",
                QQSingerDetailResponseVo.class);
        log.info(JsonMapper.obj2String(res));

        return null;
    }
}
