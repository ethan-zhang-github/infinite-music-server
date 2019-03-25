package com.just1984.music.web.component.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public interface MusicConverter<S, T> extends Converter<S, T> {

    default List<T> convert(Collection<S> src) {
        return src.stream().map(s -> convert(s)).collect(Collectors.toList());
    }
}
