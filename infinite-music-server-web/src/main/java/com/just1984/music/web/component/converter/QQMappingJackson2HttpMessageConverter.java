package com.just1984.music.web.component.converter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

@Slf4j
public class QQMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public QQMappingJackson2HttpMessageConverter() {
        setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
    }

}
