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
public class Disc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(targetEntity = Singer.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "SINGER_ID")
    private Singer singer;

    private String name;

    private String desc;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}
