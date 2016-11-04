package com.yihu.cwd.jdbc.extract

import com.yihu.cwd.jdbc.util.{SpringUtil}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Scope
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import javax.sql.DataSource
import java.util

/**
  * Created by Administrator on 2016.10.16.
  * 数据库抽取
  */
@Component
@Scope("prototype") class DBExtract[T] {
  private var jdbcTemplate: JdbcTemplate = null

  /**
    * 构造注入只读库的数据源
    *
    * @param dataSource
    */
  @Autowired(required = true) def this(@Qualifier(value = "primaryRead") dataSource: DataSource) {
    this()
    this.jdbcTemplate = new JdbcTemplate
    this.jdbcTemplate.setDataSource(dataSource)
  }

  /**
    * 不分页抽取
    *
    * @param clazz
    * @param sql
    * @return
    */
  def extract(clazz: Class[T], sql: String): util.List[T] = {
    val returnList: util.List[T] = jdbcTemplate.query(sql, new BeanPropertyRowMapper[T](clazz))
    return returnList
  }

  /**
    * 分页抽取
    *
    * @param clazz
    * @param sql
    * @param pageSize         每页显示多少 默认10000
    * @param isMultithreading 是否多线程抽取 默认否
    * @return
    */
  @throws(classOf[Exception])
  def extractByPage(clazz: Class[T], sql: String, countSql: String, pageSize: Int, isMultithreading: Boolean): util.List[T] = {
    return SpringUtil.getBean(classOf[DBPageExtract[T]]).extractByPage(clazz, sql, countSql, pageSize, isMultithreading)
  }

  /**
    * 分页抽取
    *
    * @param clazz
    * @param sql
    * @param isMultithreading 是否多线程抽取 默认否
    * @return
    */
  @throws(classOf[Exception])
  def extractByPage(clazz: Class[T], sql: String, countSql: String, isMultithreading: Boolean): util.List[T] = {
    val pageSize: Int = 10000
    return SpringUtil.getBean(classOf[DBPageExtract[T]]).extractByPage(clazz, sql, countSql, pageSize, isMultithreading)
  }

  /**
    * 分页抽取
    *
    * @param clazz
    * @param sql
    * @return
    */
  @throws(classOf[Exception])
  def extractByPage(clazz: Class[T], sql: String): util.List[T] = {
    val pageSize: Int = 10000
    val isMultithreading: Boolean = false
    val countSql: String = ""
    return SpringUtil.getBean(classOf[DBPageExtract[T]]).extractByPage(clazz, sql, countSql, pageSize, isMultithreading)
  }
}
