package com.just1984.music.persistence.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Song implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    private String mid;

    private String albumName;

    private String albumMid;

    private Long duration;

    private Long popularity;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
