package com.just1984.music.web.service.qq;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.io.CharStreams;
import com.just1984.music.core.util.JsonMapper;
import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.qq.QQResponseVo;
import com.just1984.music.model.vo.qq.QQSingerListVo;
import com.just1984.music.web.component.concurrency.MusicConcurrency;
import com.just1984.music.web.component.converter.QQSingerItemVo2SingerVoConverter;
import com.just1984.music.web.config.property.MusicProperties;
import com.just1984.music.web.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    public SingerVo getById(Long id) {
        return null;
    }
}
