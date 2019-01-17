package com.just1984.music.web.component.resolver;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.just1984.music.model.vo.qq.QQResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Slf4j
public class QQStaticResourceResolver {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static <T> QQResponseVo<T> resolve(String path) {
        try {
            Resource resource = new ClassPathResource(path);
            return objectMapper.readValue(resource.getFile(), new TypeReference<QQResponseVo<T>>() {});
        } catch (IOException e) {
            log.error("qq static resource resolve error", e);
        }
        return null;
    }
}
