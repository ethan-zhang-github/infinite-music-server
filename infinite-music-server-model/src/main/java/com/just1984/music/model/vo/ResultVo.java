package com.just1984.music.model.vo;

import com.just1984.music.model.enums.MusicExceptionEnum;
import lombok.Data;

@Data
public class ResultVo<T> {

    private final static Integer DEFAULT_CODE_SUCCESS = 200;

    private final static Integer DEFAULT_CODE_ERROR = 400;

    private final static String DEFAULT_MSG_SUCCESS = "success";

    private final static String DEFAULT_MSG_ERROR = "error";

    private Integer code;

    private String msg;

    private T data;

    public static ResultVo success(String msg, Object data) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(DEFAULT_CODE_SUCCESS);
        resultVo.setMsg(msg);
        resultVo.setData(data);
        return resultVo;
    }

    public static ResultVo success(Object data) {
        return success(DEFAULT_MSG_SUCCESS, data);
    }

    public static ResultVo success() {
        return success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return resultVo;
    }

    public static ResultVo error(String msg) {
        return error(DEFAULT_CODE_ERROR, msg);
    }

    public static ResultVo error() {
        return error(DEFAULT_MSG_ERROR);
    }

    public static ResultVo error(MusicExceptionEnum musicExceptionEnum) {
        return error(musicExceptionEnum.getCode(), musicExceptionEnum.getMsg());
    }
}
