package com.just1984.music.persistence.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(targetEntity = Singer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    @ManyToOne(targetEntity = Disc.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "DISC_ID")
    private Disc disc;

    @ManyToOne(targetEntity = Topic.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "TOPIC_ID")
    private Topic topic;

    private String name;

    private Long duration;

    private Long popularity;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;
}
