package com.just1984.music.web.component.concurrency;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MusicConcurrency {

    @Async
    public void test() {
        log.info("hello");
    }
}
