package com.yihu.cwd.controller.system

import com.yihu.cwd.controller.BaseController
import com.yihu.cwd.service.system.RoleService
import io.swagger.annotations.{ApiParam, ApiOperation, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

/**
  * Created by chenweida on 2017/3/28.
  */
@RestController
@RequestMapping(value = Array("/role"))
@Api(description ="角色")
class RoleController extends BaseController{
  @Autowired
  private var roleService: RoleService = _;

  @ApiOperation("新增角色")
  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def addMenu(
               @ApiParam(name = "name", value = "名称", required = true) @RequestParam(value = "name", required = true) name: String,
               @ApiParam(name = "remark", value = "备注", required = true) @RequestParam(value = "remark", required = true) remark: String): String = {
    try {
      roleService.add(name,  remark);
      success("新增成功")
    } catch {
      case e: Exception => {
        error(-1, e.getMessage)
      }
    }
  }
}
