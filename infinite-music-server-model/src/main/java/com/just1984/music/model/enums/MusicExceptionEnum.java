package com.just1984.music.model.enums;

import lombok.Getter;

@Getter
public enum MusicExceptionEnum {

    ERROR(400, "error");

    private Integer code;

    private String msg;

    MusicExceptionEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
