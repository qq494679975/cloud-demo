package com.yihu.cwd.service

import com.yihu.cwd.dao.TestDao
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

import scala.util.parsing.json.JSON

/**
  * Created by chenweida on 2017/3/28.
  */
@Service
class TestService {

  @Autowired
  var testDao: TestDao = _



  def test(id: String):String ={
    testDao.findOne(id).toString
  }
}
