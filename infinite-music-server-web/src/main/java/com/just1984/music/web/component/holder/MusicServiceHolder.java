package com.just1984.music.web.component.holder;

import com.just1984.music.web.service.DiscService;
import com.just1984.music.web.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MusicServiceHolder {

    @Autowired
    private Map<String, RecommendService> recommendServiceMap;

    @Autowired
    private Map<String, DiscService> discServiceMap;

    public RecommendService getRecommendService(String prefix) {
        return recommendServiceMap.get(prefix.toLowerCase() + "RecommendService");
    }

    public DiscService getDiscService(String prefix) {
        return discServiceMap.get(prefix.toLowerCase() + "DiscService");
    }
}
