package com.sumeet.erp.common.web;

import java.util.Objects;
import java.util.concurrent.Executor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.core.task.support.TaskExecutorAdapter;

@Configuration
public class VirtualThreadConfig {

  @Bean
  public Executor taskExecutor() {
    return java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor();
  }

  @Bean
  public AsyncTaskExecutor applicationTaskExecutor(Executor executor) {
    Objects.requireNonNull(executor, "executor must not be null");
    return new TaskExecutorAdapter(executor);
  }
}