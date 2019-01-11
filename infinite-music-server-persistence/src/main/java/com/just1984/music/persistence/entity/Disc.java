package com.just1984.music.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Disc {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Singer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    private String name;

    private String desc;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;

}
