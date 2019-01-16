package com.just1984.music.persistence.entity;

import com.just1984.music.model.enums.ResourceOriginEnum;
import com.just1984.music.model.enums.ResourceTypeEnum;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class Resource implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ResourceTypeEnum type;

    @Enumerated(EnumType.STRING)
    private ResourceOriginEnum origin;

    private String url;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;

    public Resource(ResourceTypeEnum type, ResourceOriginEnum origin, String url) {
        this.type = type;
        this.origin = origin;
        this.url = url;
    }
}
