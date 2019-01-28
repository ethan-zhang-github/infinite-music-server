package com.just1984.music.model.vo;

import lombok.Data;

@Data
public class SongVo {

    private Long id;

    private String name;

    private String mid;

    private String albumName;

    private String albumMid;

    private Long duration;

    private String url;

    private String imgUrl;

    private SingerVo singer;
}
