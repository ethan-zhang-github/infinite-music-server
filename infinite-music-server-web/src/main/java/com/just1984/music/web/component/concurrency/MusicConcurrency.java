package com.just1984.music.web.component.concurrency;

import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.web.service.LocalRecommendService;
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

    @Async
    public void saveRecommendList(List<RecommendVo> recommendVoList) {
        localRecommendService.saveRecommendList(recommendVoList);
    }
}
