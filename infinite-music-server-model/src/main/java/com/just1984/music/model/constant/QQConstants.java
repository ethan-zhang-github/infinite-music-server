package com.just1984.music.model.constant;

import java.util.HashMap;
import java.util.Map;

public class QQConstants {

    /**
     * QQ音乐接口请求通用参数
     */
    public final static Map<String, Object> commonParams = new HashMap<>();

    static {
        commonParams.put("g_tk", 1928093487);
        commonParams.put("inCharset", "utf-8");
        commonParams.put("outCharset", "utf-8");
        commonParams.put("notice", 0);
        commonParams.put("format", "jsonp");
    }

}
