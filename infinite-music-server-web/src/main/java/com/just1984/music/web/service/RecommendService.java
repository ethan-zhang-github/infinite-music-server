package com.just1984.music.web.service;

import com.just1984.music.model.vo.RecommendVo;

import java.util.List;

public interface RecommendService {

    List<RecommendVo> getRecommendList(int size);

}
