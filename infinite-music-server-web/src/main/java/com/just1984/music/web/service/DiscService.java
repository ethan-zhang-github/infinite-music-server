package com.just1984.music.web.service;

import com.just1984.music.model.vo.DiscVo;

import java.util.List;

public interface DiscService {

    List<DiscVo> getDiscList(int size);
}
