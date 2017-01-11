package com.cwd.logback;

import ch.qos.logback.core.spi.ContextAware;
import org.apache.flume.Event;
import org.apache.flume.EventDeliveryException;
import org.apache.flume.api.RpcClient;
import org.apache.flume.api.RpcClientFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.*;

/**
 * 日志发送器
 */
public class EventReporter {

    private RpcClient client;

    private final ContextAware loggingContext;

    private final ExecutorService es;

    private final Properties connectionProps;

    public EventReporter(final Properties properties, final ContextAware context,
                         final int maximumThreadPoolSize, final int maxQueueSize) {
        BlockingQueue<Runnable> blockingQueue = new ArrayBlockingQueue<Runnable>(maxQueueSize);
        this.connectionProps = properties;
        this.loggingContext = context;

        int corePoolSize = 1;
        TimeUnit threadKeepAliveUnits = TimeUnit.SECONDS;
        int threadKeepAliveTime = 30;
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy();

        this.es = new ThreadPoolExecutor(corePoolSize, maximumThreadPoolSize, threadKeepAliveTime,
                threadKeepAliveUnits, blockingQueue, handler);
    }

    public void report(final Event[] events) {
        es.submit(new ReportingJob(events));
    }

    private synchronized RpcClient createClient() {
        if (client == null) {
            loggingContext.addInfo("初始化flume客户端: " + connectionProps);
            try {
                client = RpcClientFactory.getInstance(connectionProps);
            } catch (Exception e) {
                loggingContext.addError(e.getLocalizedMessage(), e);
            }
        }

        return client;
    }

    public synchronized void close() {
        loggingContext.addInfo("关闭flume客户端");
        if (client != null) {
            client.close();
            client = null;
        }
    }

    public void shutdown() {
        close();
        es.shutdown();
    }

    private class ReportingJob implements Runnable {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private static final int retries = 3;

        private final Event[] events;

        public ReportingJob(final Event[] events) {
            this.events = events;
            logger.debug("创建Job发送日志", events.length);
        }


        @Override
        public void run() {
            boolean success = false;
            int count = 0;
            try {
                while (!success && count < retries) {
                    count++;
                    try {
                        logger.debug("开始批量提交日志", events.length, count);
                        createClient().appendBatch(Arrays.asList(events));
                        success = true;
                        logger.debug("提交日志成功", events.length);
                    } catch (EventDeliveryException e) {
                        logger.warn(e.getLocalizedMessage(), e);
                        logger.warn("重试提交日志" + (retries - count) + " 次");
                    }
                }
            } finally {
                if (!success) {
                    logger.error("提交日志失败");
                    close();
                }
            }
        }
    }
}
