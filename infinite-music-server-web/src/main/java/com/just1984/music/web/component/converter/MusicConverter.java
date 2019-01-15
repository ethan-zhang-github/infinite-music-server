package com.just1984.music.web.component.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Collection;
import java.util.List;

public interface MusicConverter<S, T> extends Converter<S, T> {

    List<T> convert(Collection<S> src);
}
