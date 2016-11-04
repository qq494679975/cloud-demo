package com.yihu.cwd.async

import java.util.concurrent.{RejectedExecutionHandler, Executor}
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy

import java.util.concurrent.ThreadPoolExecutor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor

/**
  * Created by Administrator on 2016.10.25.
  */
@Configuration
@EnableAsync
class AsyncConfig {
  /** 如果池中的实际线程数小于corePoolSize,无论是否其中有空闲的线程，都会给新的任务产生新的线程 */
  private var corePoolSize: Int = 5
  /** 如果池中的线程数＝maximumPoolSize，则有空闲线程使用空闲线程，否则新任务放入queueCapacity. */
  private var maxPoolSize: Int = 20
  /** 缓冲队列. */
  private var queueCapacity: Int = 10

  @Bean
  def dbExtractExecutor: Executor = {
    var executor: ThreadPoolTaskExecutor = new ThreadPoolTaskExecutor()
    executor.setCorePoolSize(corePoolSize)
    executor.setMaxPoolSize(maxPoolSize)
    executor.setQueueCapacity(queueCapacity)
    executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy())
    executor.initialize()
    return executor
  }

  @Bean
  def dbStorageExecutor: Executor = {
      var executor: ThreadPoolTaskExecutor = new ThreadPoolTaskExecutor
      executor.setCorePoolSize(corePoolSize)
      executor.setMaxPoolSize(maxPoolSize)
      executor.setQueueCapacity(queueCapacity)
      executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy)
      executor.initialize
      return executor
  }
}
