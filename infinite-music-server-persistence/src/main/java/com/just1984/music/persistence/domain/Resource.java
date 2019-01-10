package com.just1984.music.persistence.domain;

import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Resource {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceTypeEnum type;

    @Enumerated(EnumType.STRING)
    private ResourceOriginEnum origin;

    private String url;
}
