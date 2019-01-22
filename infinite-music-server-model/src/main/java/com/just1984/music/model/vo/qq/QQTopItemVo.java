package com.just1984.music.model.vo.qq;

import lombok.Data;

import java.util.List;

@Data
public class QQTopItemVo {

    private Long id;

    private Long listenCount;

    private String picUrl;

    private String topTitle;

    private List<QQTopSongVo> songList;
}
