package com.just1984.music.web.service;

import com.just1984.music.model.vo.SingerVo;

import java.util.List;

public interface SingerService {

    List<SingerVo> getSingerList(int size);

    SingerVo getById(String id);
}
