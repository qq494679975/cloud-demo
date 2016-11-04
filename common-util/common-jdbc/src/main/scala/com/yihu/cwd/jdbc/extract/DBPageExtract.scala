package com.yihu.cwd.jdbc.extract

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Scope
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.stereotype.Component
import javax.sql.DataSource
import java.util

import org.springframework.util.StringUtils

import scala.util.control.Breaks

/**
  * Created by Administrator on 2016.10.16.
  * 分页抽取器
  */
@Component
@Scope("prototype")
class DBPageExtract[T] {
  private var jdbcTemplate: JdbcTemplate = null

  /**
    * 构造注入只读库的数据源
    *
    * @param dataSource
    */
  @Autowired(required = true)
  def this(@Qualifier(value = "primaryRead") dataSource: DataSource) {
    this()
    this.jdbcTemplate = new JdbcTemplate
    this.jdbcTemplate.setDataSource(dataSource)
  }

  private var returnList: util.List[T] = new util.ArrayList[T]

  def addData(dataList: util.List[T]) {
    returnList.addAll(dataList)
  }

  @throws(classOf[Exception])
  def extractByPage(clazz: Class[T], sql: String, countSql: String, pageSize: Int, isMultithreading: Boolean): util.List[T] = {
    if (isMultithreading) {
      if (StringUtils.isEmpty(countSql)) {
        throw new Exception("countSql is null")
      }
      return MultiThreadExtract(clazz, sql, countSql, pageSize)
    }
    else {
      return noMultiThreadExtract(clazz, sql, pageSize)
    }
  }

  /**
    * 不用多线程抽取
    *
    * @param clazz
    * @param sql
    * @param pageSize
    * @return
    */
  private def noMultiThreadExtract(clazz: Class[T], sql: String, pageSize: Int): util.List[T] = {
    val returnList: util.List[T] = new util.ArrayList[T]
    var start: Int = 0
    var page: Int = 1
    while (true) {
      val finalSql: String = sql + " limit " + start + "," + pageSize
      val listTemp: util.List[T] = jdbcTemplate.query(finalSql, new BeanPropertyRowMapper[T](clazz))
      returnList.addAll(listTemp)
      if (listTemp.size != pageSize) {
        listTemp.clear

      }
      else {
        start = page * pageSize
        page += 1
        listTemp.clear
      }
    }
    return returnList
  }

  /**
    * 多线程抽取数据
    *
    * @param clazz
    * @param sql
    * @param pageSize
    * @return
    */
  private def MultiThreadExtract(clazz: Class[T], sql: String, countSql: String, pageSize: Int): util.List[T] = {
    try {
      val dataCount: Int = getCount(countSql)//得到数据的总数
      var forCount: Int = getForCount(dataCount, pageSize) //根据count 计算出 总共要执行几次
      forCount+=1;//自增长1
      val asyncResultPool: util.List[AsyncResult[Boolean]] = new util.ArrayList[AsyncResult[Boolean]]//綫程返回值的值
      for (page <- 0 to forCount){
        val future: AsyncResult[Boolean] = multiExtractData(sql, page * pageSize, pageSize, clazz)//拆分成多个线程抽取数据
        asyncResultPool.add(future)
      }

      val loop = new Breaks;// 创建 Breaks 对象
      loop.breakable{// 在 breakable 中循环
        val asyncResultIterator: util.Iterator[AsyncResult[Boolean]] = asyncResultPool.iterator
        while (asyncResultIterator.hasNext) {
          //得到迭代器
          val asyncResult: AsyncResult[Boolean] = asyncResultIterator.next
          if (asyncResult.isDone) {
            //如果做完了就移除迭代器
            asyncResultIterator.remove();
          }else {
            Thread.sleep(500L)
          }
        }
        //如果一直移除到没有下一个就跳出循环 说明数据已经采集完成
        if (!asyncResultIterator.hasNext) {
          loop.break();// 循环中断
        }
      }
      return returnList
    }
    catch {
      case e: Exception => {
        return noMultiThreadExtract(clazz, sql, pageSize)
      }
    }
  }

  private def getForCount(dataCount: Int, pageSize: Int): Int = {
    val yushu: Int = dataCount % pageSize
    var page: Int = dataCount / pageSize
    if (yushu == 0) {
      return page
    }
    else {
      page += 1
      return page
    }
  }

  private def getCount(countSql: String): Int = {
    val countMap: Int = jdbcTemplate.queryForObject(countSql, classOf[Int])
    return countMap
  }

  /**
    * 多线程采集数据
    *
    * @param sql
    * @param start
    * @param pageSize
    * @param clazz
    * @return
    */
  @Async("dbExtractExecutor")
  private def multiExtractData(sql: String, start: Int, pageSize: Int, clazz: Class[T]): AsyncResult[Boolean] = {
    val finalSql: String = sql + " limit " + start + "," + pageSize //拼凑分页的语句
    val listTemp: util.List[T] = jdbcTemplate.query(finalSql, new BeanPropertyRowMapper[T](clazz))
    addData(listTemp)
    return new AsyncResult[Boolean](true)
  }
}
