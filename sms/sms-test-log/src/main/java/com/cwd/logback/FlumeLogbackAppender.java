package com.cwd.logback;

import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Layout;
import ch.qos.logback.core.UnsynchronizedAppenderBase;
import org.apache.commons.lang.StringUtils;
import org.apache.flume.Event;
import org.apache.flume.FlumeException;
import org.apache.flume.event.EventBuilder;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.Charset;
import java.util.*;

/**
 * FlumeLogbackAppender
 */
public class FlumeLogbackAppender extends UnsynchronizedAppenderBase<ILoggingEvent> {

    protected static final Charset UTF_8 = Charset.forName("UTF-8");
    //flume链接管理器
    private FlumeAvroManager flumeManager;
    //flume服务器
    private String flumeAgents;
    //flume配置
    private String flumeProperties;

    private Long reportingWindow;

    private Integer batchSize;

    private Integer reporterMaxThreadPoolSize;

    private Integer reporterMaxQueueSize;

    private Map<String, String> additionalAvroHeaders;

    private String application;

    protected Layout<ILoggingEvent> layout;

    private String hostname;

    private String type;


    @Override
    public void start() {
        if (StringUtils.isEmpty(application)) {
            application = resolveApplication();
        }
        //初始化flume的参数
        if (StringUtils.isNotEmpty(flumeAgents)) {
            String[] agentConfigs = flumeAgents.split(",");

            List<RemoteFlumeAgent> agents = new ArrayList<RemoteFlumeAgent>(agentConfigs.length);
            for (String conf : agentConfigs) {
                RemoteFlumeAgent agent = RemoteFlumeAgent.fromString(conf.trim());
                if (agent != null) {
                    agents.add(agent);
                } else {
                    addWarn("Flume参数初始化失败 '" + conf + "'");
                }
            }
            Properties overrides = new Properties();
            overrides.putAll(extractProperties(flumeProperties));
            //创建flume链接
            flumeManager = FlumeAvroManager.create(agents, overrides,
                    batchSize, reportingWindow, reporterMaxThreadPoolSize, reporterMaxQueueSize, this);
        } else {
            addError("Flume参数为空");
        }
        super.start();

    }

    /**
     * 获取参数
     *
     * @param propertiesAsString
     * @return
     */
    private Map<String, String> extractProperties(String propertiesAsString) {
        final Map<String, String> props = new HashMap<String, String>();
        if (StringUtils.isNotEmpty(propertiesAsString)) {
            final String[] segments = propertiesAsString.split(";");
            for (final String segment : segments) {
                final String[] pair = segment.split("=");
                if (pair.length == 2) {
                    final String key = StringUtils.strip(pair[0]);
                    final String value = StringUtils.strip(pair[1]);
                    if (StringUtils.isNotEmpty(key) && StringUtils.isNotEmpty(value)) {
                        props.put(key, value);
                    } else {
                        addWarn(" key或者value为空: " + segment);
                    }
                } else {
                    addWarn(" 格式错误 " + segment);
                }
            }
        } else {
            addInfo("没有需要初始化Flume的参数");
        }

        return props;
    }

    @Override
    public void stop() {
        try {
            if (flumeManager != null) {
                flumeManager.stop();
            }
        } catch (FlumeException fe) {
            addWarn(fe.getMessage(), fe);
        }
    }

    @Override
    protected void append(ILoggingEvent eventObject) {

        if (flumeManager != null) {
            try {
                String body = layout != null ? layout.doLayout(eventObject) : eventObject.getFormattedMessage();
                Map<String, String> headers = new HashMap<String, String>();
                if (additionalAvroHeaders != null) {
                    headers.putAll(additionalAvroHeaders);
                }
                headers.putAll(extractHeaders(eventObject));

                Event event = EventBuilder.withBody(StringUtils.strip(body), UTF_8, headers);

                flumeManager.send(event);
            } catch (Exception e) {
                addError(e.getLocalizedMessage(), e);
            }
        }

    }

    /**
     * 发送消息的封装
     *
     * @param eventObject
     * @return
     */
    private Map<String, String> extractHeaders(ILoggingEvent eventObject) {
        Map<String, String> headers = new HashMap<String, String>(10);
        headers.put("timestamp", Long.toString(eventObject.getTimeStamp()));
        headers.put("type", eventObject.getLevel().toString());
        headers.put("logger", eventObject.getLoggerName());
        headers.put("message", eventObject.getMessage());
        headers.put("level", eventObject.getLevel().toString());
        try {
            headers.put("host", resolveHostname());
        } catch (UnknownHostException e) {
            addWarn(e.getMessage());
        }
        headers.put("thread", eventObject.getThreadName());
        if (StringUtils.isNotEmpty(application)) {
            headers.put("application", application);
        }

        if (StringUtils.isNotEmpty(type)) {
            headers.put("type", type);
        }

        return headers;
    }

    private String resolveHostname() throws UnknownHostException {
        return hostname != null ? hostname : InetAddress.getLocalHost().getHostName();
    }

    private String resolveApplication() {
        return System.getProperty("application.name");
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public void setLayout(Layout<ILoggingEvent> layout) {
        this.layout = layout;
    }

    public void setFlumeAgents(String flumeAgents) {
        this.flumeAgents = flumeAgents;
    }

    public void setFlumeProperties(String flumeProperties) {
        this.flumeProperties = flumeProperties;
    }

    public void setAdditionalAvroHeaders(String additionalHeaders) {
        this.additionalAvroHeaders = extractProperties(additionalHeaders);
    }

    public void setBatchSize(String batchSizeStr) {
        try {
            this.batchSize = Integer.parseInt(batchSizeStr);
        } catch (NumberFormatException nfe) {
            addWarn("参数初始化失败batchSize to " + batchSizeStr, nfe);
        }
    }

    public void setReportingWindow(String reportingWindowStr) {
        try {
            this.reportingWindow = Long.parseLong(reportingWindowStr);
        } catch (NumberFormatException nfe) {
            addWarn("参数初始化失败 reportingWindow to " + reportingWindowStr, nfe);
        }
    }


    public void setReporterMaxThreadPoolSize(String reporterMaxThreadPoolSizeStr) {
        try {
            this.reporterMaxThreadPoolSize = Integer.parseInt(reporterMaxThreadPoolSizeStr);
        } catch (NumberFormatException nfe) {
            addWarn("参数初始化失败 reporterMaxThreadPoolSize to " + reporterMaxThreadPoolSizeStr, nfe);
        }
    }

    public void setReporterMaxQueueSize(String reporterMaxQueueSizeStr) {
        try {
            this.reporterMaxQueueSize = Integer.parseInt(reporterMaxQueueSizeStr);
        } catch (NumberFormatException nfe) {
            addWarn("参数初始化失败 reporterMaxQueueSize to " + reporterMaxQueueSizeStr, nfe);
        }
    }
}
