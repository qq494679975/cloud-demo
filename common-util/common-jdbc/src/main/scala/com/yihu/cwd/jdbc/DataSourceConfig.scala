package com.yihu.cwd.jdbc

import org.springframework.context.annotation.Configuration

/**
  * Created by Administrator on 2016.10.25.
  */
@Configuration
class DataSourceConfig {
  //    /**
  //     *  主数据源
  //     * @return
  //     */
  //    @Bean(name = "primaryReadWrite")
  //    @Primary//主库 默认不写名字用这个
  //    @ConfigurationProperties(prefix="spring.datasource.primaryReadWrite")
  //    public DataSource primaryReadWriteDataSource() {
  //        return DataSourceBuilder.create().build();
  //    }
  //
  //    @Bean(name = "primaryRead")
  //    @ConfigurationProperties(prefix="spring.datasource.primaryRead")
  //    public DataSource primaryReadDataSource() {
  //        return DataSourceBuilder.create().build();
  //    }

}
