package com.just1984.music.web.config.property;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "music")
@Getter
@Setter
public class MusicProperties {

    private String profile;

    private QQProperties qq;
}
