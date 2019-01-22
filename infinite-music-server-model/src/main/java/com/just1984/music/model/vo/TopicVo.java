package com.just1984.music.model.vo;

import lombok.Data;

import java.util.List;

@Data
public class TopicVo {

    private Long id;

    private String name;

    private String picUrl;

    private List<SongVo> songList;
}
