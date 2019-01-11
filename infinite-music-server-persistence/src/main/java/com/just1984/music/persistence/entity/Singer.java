package com.just1984.music.persistence.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class Singer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String otherName;

    private String alphabet;

    private String gender;

    private Integer age;

    private boolean isHot;

    private String keywordIds;

    private String resourceIds;

}
