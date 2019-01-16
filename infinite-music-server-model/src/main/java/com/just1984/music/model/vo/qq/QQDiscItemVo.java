package com.just1984.music.model.vo.qq;

import lombok.Data;

@Data
public class QQDiscItemVo {

    private Long dissid;

    private String dissname;

    private String imgurl;

    private QQSingerVo creator;
}
