package com.cwd.logback;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FlumeAgent的抽象封装
 */
public final class RemoteFlumeAgent {

    private static final Logger logger = LoggerFactory.getLogger(RemoteFlumeAgent.class);

    private final String hostname;

    private final int port;

    public RemoteFlumeAgent(final String hostname, final int port) {
        this.hostname = hostname;
        this.port = port;
    }

    public String getHostname() {
        return hostname;
    }

    public int getPort() {
        return port;
    }

    public static RemoteFlumeAgent fromString(String input) {
        if (StringUtils.isNotEmpty(input)) {

            String[] parts = input.split(":");
            if (parts.length == 2) {

                String portString = parts[1];
                try {
                    int port = Integer.parseInt(portString);
                    return new RemoteFlumeAgent(parts[0], port);
                } catch (NumberFormatException nfe) {
                    logger.error("端口参数不是数字: " + portString);
                }
            } else {
                logger.error("flume服务器参数格式有错: " + input);
            }
        } else {
            logger.error("flume服务器参数为空");
        }
        return null;
    }
}
