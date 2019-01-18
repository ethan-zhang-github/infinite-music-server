package com.just1984.music.model.vo.qq;

import lombok.Data;

import java.util.List;

@Data
public class QQMusicDataVo {

    private Long songid;

    private String songmid;

    private String songname;

    private String albummid;

    private String albumname;

    private Long interval;

    private List<QQSingerVo> singer;
}
