package com.just1984.music.web.config;

import com.just1984.music.web.component.converter.JsonpMappingJackson2HttpMessageConverter;
import com.just1984.music.web.component.converter.QQMappingJackson2HttpMessageConverter;
import com.just1984.music.web.component.interceptor.MusicClientHttpRequestInterceptor;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@SpringBootConfiguration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory){
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(new QQMappingJackson2HttpMessageConverter());
        restTemplate.getMessageConverters().add(new JsonpMappingJackson2HttpMessageConverter());
        //restTemplate.getInterceptors().add(new MusicClientHttpRequestInterceptor());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory(){
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(30 * 1000);
        factory.setConnectTimeout(30 * 1000);
        return factory;
    }
}
