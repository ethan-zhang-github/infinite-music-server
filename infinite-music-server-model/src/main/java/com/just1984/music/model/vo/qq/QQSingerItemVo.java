package com.just1984.music.model.vo.qq;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.Data;

@Data
@JsonAutoDetect(fieldVisibility=JsonAutoDetect.Visibility.ANY, getterVisibility=JsonAutoDetect.Visibility.NONE)
public class QQSingerItemVo {

    private String Fsinger_id;

    private String Findex;

    private String Fsinger_name;

    private String Fother_name;
}
