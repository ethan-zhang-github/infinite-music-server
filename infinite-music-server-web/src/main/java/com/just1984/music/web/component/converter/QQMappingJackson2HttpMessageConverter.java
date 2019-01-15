package com.just1984.music.web.component.converter;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.Arrays;

public class QQMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public QQMappingJackson2HttpMessageConverter() {
        setSupportedMediaTypes(Arrays.asList(MediaType.TEXT_HTML));
    }
}
