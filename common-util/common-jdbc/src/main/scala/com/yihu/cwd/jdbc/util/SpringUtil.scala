package com.yihu.cwd.jdbc.util

import org.springframework.beans.BeansException
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component

@Component
object SpringUtil {
  private var applicationContext: ApplicationContext = null

  def getApplicationContext: ApplicationContext = {
    return applicationContext
  }

  def getBean(name: String): AnyRef = {
    return getApplicationContext.getBean(name)
  }

  def getBean[T](clazz: Class[T]): T = {
    return getApplicationContext.getBean(clazz)
  }

  def getBean[T](name: String, clazz: Class[T]): T = {
    return getApplicationContext.getBean(name, clazz)
  }
}

@Component
class SpringUtil extends ApplicationContextAware {
  @throws(classOf[BeansException])
  def setApplicationContext(applicationContext: ApplicationContext) {
    if (SpringUtil.applicationContext == null) {
      SpringUtil.applicationContext = applicationContext
    }
  }
}