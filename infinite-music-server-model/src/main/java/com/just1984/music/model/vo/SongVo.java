package com.just1984.music.model.vo;

import lombok.Data;

@Data
public class SongVo {

    private Long id;

    private String name;

    private SingerVo singer;
}
