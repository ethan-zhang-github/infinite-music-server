package com.just1984.music.web.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.stream.Collectors;

/**
 * @description: @Async使用自定义线程池
 * @author: zhangyifan@wshifu.com
 * @date: 2019-03-22 17:52
 */
@Slf4j
@Component
public class MusicAsyncConfigurer implements AsyncConfigurer {

    @Autowired
    private TaskExecutor taskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return ((throwable, method, objects) -> {
            log.info("多线程执行异常，message:【{}】，method:【{}】，params:【{}】", throwable.getMessage(),
                    method.getName(), Arrays.stream(objects).map(Object::toString).collect(Collectors.joining(", ")));
        });
    }
}
