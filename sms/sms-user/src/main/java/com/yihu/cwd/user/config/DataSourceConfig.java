package com.yihu.cwd.user.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * Created by Administrator on 2016.10.25.
 */

import javax.sql.DataSource;

@Configuration
class DataSourceConfig {
    //    /**
    //     *  主数据源
    //     * @return
    //     */
    @Bean
    @Primary//主库 默认不写名字用这个
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource primaryReadWriteDataSource() {
        return DataSourceBuilder.create().build();
    }
    //
    //    @Bean(name = "primaryRead")
    //    @ConfigurationProperties(prefix="spring.datasource.primaryRead")
    //    public DataSource primaryReadDataSource() {
    //        return DataSourceBuilder.create().build();
    //    }

}
