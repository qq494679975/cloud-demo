package com.yihu.cwd.quartz

import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.quartz.spi.TriggerFiredBundle
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.AutowireCapableBeanFactory
import org.springframework.scheduling.quartz.AdaptableJobFactory
import org.springframework.stereotype.Component

/**
  * Created by Administrator on 2016.10.25.
*/
@Component("jobFactory")
class JobFactory extends AdaptableJobFactory {
  @Autowired private var capableBeanFactory: AutowireCapableBeanFactory = null

  @throws(classOf[Exception])
  protected override def createJobInstance(bundle: TriggerFiredBundle): AnyRef = {
    val jobInstance: AnyRef = super.createJobInstance(bundle)
    capableBeanFactory.autowireBean(jobInstance)
    return jobInstance
  }
}
