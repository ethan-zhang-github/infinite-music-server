package com.just1984.music.model.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingerVo {

    private Long id;

    private String mid;

    private String index;

    private String name;

    private String otherName;

    private String avatar;
}
