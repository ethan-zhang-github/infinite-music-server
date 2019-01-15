package com.just1984.music.model.vo.qq;

import lombok.Data;

@Data
public class QQResponseVo<T> {

    private final static int QQ_CODE_SUCCESS = 0;

    private int code;

    private T data;
}
