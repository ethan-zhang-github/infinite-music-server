package com.just1984.music.model.vo;

import lombok.Data;

@Data
public class DiscVo {

    private Long id;

    private String name;

    private String imgUrl;

    private SingerVo singer;
}
