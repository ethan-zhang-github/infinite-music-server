package com.just1984.music.web.component.concurrency;

import com.just1984.music.model.vo.DiscVo;
import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.model.vo.SingerVo;
import com.just1984.music.model.vo.TopicVo;
import com.just1984.music.web.service.local.LocalDiscService;
import com.just1984.music.web.service.local.LocalRecommendService;
import com.just1984.music.web.service.local.LocalSingerService;
import com.just1984.music.web.service.local.LocalTopicService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class MusicConcurrency {

    @Autowired
    private LocalRecommendService localRecommendService;

    @Autowired
    private LocalDiscService localDiscService;

    @Autowired
    private LocalSingerService localSingerService;

    @Autowired
    private LocalTopicService localTopicService;

    @Async
    public void saveRecommendList(List<RecommendVo> recommendVoList) {
        localRecommendService.saveRecommendList(recommendVoList);
    }

    @Async
    public void saveDiscList(List<DiscVo> discVoList) {
        localDiscService.saveDiscList(discVoList);
    }

    @Async
    public void saveSingerList(List<SingerVo> singerVoList) {
        localSingerService.saveSingerList(singerVoList);
    }

    @Async
    public void saveTopicList(List<TopicVo> topicVoList) {
        localTopicService.saveTopicList(topicVoList);
    }
}
