package com.just1984.music.web.component.holder;

import com.just1984.music.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MusicServiceHolder {

    @Autowired
    private Map<String, RecommendService> recommendServiceMap;

    public RecommendService getRecommendService(String prefix) {
        return recommendServiceMap.get(prefix.toLowerCase() + "RecommendService");
    }
}
