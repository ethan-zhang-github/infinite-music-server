package com.just1984.music.web.service;

import com.just1984.music.model.vo.SongVo;
import com.just1984.music.model.vo.TopicVo;

import java.util.List;

public interface TopicService {

    List<TopicVo> getTopicList(int size);

    List<SongVo> getSongListByTopicId(String id, int size);
}
