package com.just1984.music.web.component.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.google.common.io.CharStreams;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonInputMessage;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

@Slf4j
public class JsonpMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {

    public JsonpMappingJackson2HttpMessageConverter() {
        setSupportedMediaTypes(Arrays.asList(MediaType.valueOf("application/x-javascript")));
    }

    @Override
    public Object read(Type type, @Nullable Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        JavaType javaType = this.getJavaType(type, contextClass);
        return this.readJavaType(javaType, inputMessage);
    }

    private Object readJavaType(JavaType javaType, HttpInputMessage inputMessage) throws IOException {
        try {
            if (inputMessage instanceof MappingJacksonInputMessage) {
                Class<?> deserializationView = ((MappingJacksonInputMessage)inputMessage).getDeserializationView();
                if (deserializationView != null) {
                    return this.objectMapper.readerWithView(deserializationView).forType(javaType).readValue(inputMessage.getBody());
                }
            }

            InputStream body = inputMessage.getBody();
            String bodyStr = CharStreams.toString(new InputStreamReader(body, StandardCharsets.UTF_8));
            log.info(bodyStr);
            String jsonStr = bodyStr.substring(bodyStr.indexOf("(") + 1, bodyStr.lastIndexOf(")"));
            log.info(jsonStr);
            return this.objectMapper.readValue(jsonStr, javaType);
        } catch (InvalidDefinitionException var4) {
            throw new HttpMessageConversionException("Type definition error: " + var4.getType(), var4);
        } catch (JsonProcessingException var5) {
            throw new HttpMessageNotReadableException("JSON parse error: " + var5.getOriginalMessage(), var5, inputMessage);
        }
    }
}
