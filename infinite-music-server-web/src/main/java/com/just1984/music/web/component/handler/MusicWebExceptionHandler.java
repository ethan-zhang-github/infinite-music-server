package com.just1984.music.web.component.handler;

import com.just1984.music.model.exception.MusicException;
import com.just1984.music.model.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MusicWebExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResultVo handleCommonException(Exception ex) {
        log.error("catch common exception {}", ex);
        return ResultVo.error(ex.getMessage());
    }

    @ExceptionHandler(MusicException.class)
    public ResultVo handleMusicException(MusicException ex) {
        log.error("cat music exception, code:{}, message:{}", ex.getCode(), ex.getMessage());
        return ResultVo.error(ex.getCode(), ex.getMessage());
    }
}
