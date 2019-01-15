package com.just1984.music.web.service;

import com.just1984.music.model.vo.RecommendVo;
import com.just1984.music.persistence.entity.Recommend;
import com.just1984.music.persistence.repository.RecommendRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("localRecommendService")
public class LocalRecommendService implements RecommendService {

    @Autowired
    private RecommendRepository recommendRepository;

    @Override
    public List<RecommendVo> getRecommendList(int size) {
        List<Recommend> recommendList = recommendRepository.getLatestRecommendList(size);
        return null;
    }
}
