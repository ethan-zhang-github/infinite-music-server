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
public class Singer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long originId;

    private String mid;

    private String name;

    private String otherName;

    private String alphabet;

    private String gender;

    private Integer age;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

}
