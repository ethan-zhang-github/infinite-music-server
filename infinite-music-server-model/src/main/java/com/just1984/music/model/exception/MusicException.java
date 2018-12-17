package com.just1984.music.model.exception;

import com.just1984.music.model.enums.MusicExceptionEnum;
import lombok.Getter;

@Getter
public class MusicException extends RuntimeException {

    private Integer code;

    public MusicException(MusicExceptionEnum musicExceptionEnum) {
        super(musicExceptionEnum.getMsg());
        this.code = musicExceptionEnum.getCode();
    }

    public MusicException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }
}
