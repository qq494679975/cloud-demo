package com.yihu.cwd.quartz

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.PropertiesFactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.scheduling.quartz.SchedulerFactoryBean
import javax.sql.DataSource
import java.io.IOException
import java.util.Properties

/**
  * Created by Administrator on 2016.10.25.
  */
@Configuration
class SchedulerConfig {
  @Autowired private var applicationContext: ApplicationContext = null
  @Autowired private var jobFactory: JobFactory = null
  @Autowired private var dataSource: DataSource = null

  @Bean
  @throws(classOf[IOException])
  private[quartz] def schedulerFactoryBean: SchedulerFactoryBean = {
    val bean: SchedulerFactoryBean = new SchedulerFactoryBean
    bean.setJobFactory(jobFactory)
    bean.setApplicationContext(this.applicationContext)
    bean.setOverwriteExistingJobs(true)
    bean.setStartupDelay(20)
    bean.setAutoStartup(true)
    bean.setDataSource(dataSource)
    bean.setQuartzProperties(quartzProperties)
    return bean
  }

  /**
    * quartz配置文件
    *
    * @return
    * @throws IOException
    */
  @Bean
  @throws(classOf[IOException])
  def quartzProperties: Properties = {
    val propertiesFactoryBean: PropertiesFactoryBean = new PropertiesFactoryBean
    propertiesFactoryBean.setLocation(new ClassPathResource("/quartz.properties"))
    propertiesFactoryBean.afterPropertiesSet
    return propertiesFactoryBean.getObject
  }
}
