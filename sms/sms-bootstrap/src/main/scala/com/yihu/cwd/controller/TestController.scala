package com.yihu.cwd.controller

import com.yihu.cwd.service.TestService
import io.swagger.annotations.{ApiParam, ApiOperation, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

/**
  * Created by chenweida on 2017/3/28.
  */
@RestController
@RequestMapping(value = Array("/test"))
@Api(description = "测试")
class TestController {
  @Autowired
  var testService: TestService = _

  @ApiOperation(value = "测试")
  @RequestMapping(value = Array("/"), method = Array(RequestMethod.GET))
  def test(
            @ApiParam(name = "id", value = "测试id", required = true, defaultValue = "test") @RequestParam(value = "id", required = true) id: String): String = {
    return testService.test(id)
  }
}
