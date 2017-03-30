package com.yihu.cwd.controller.system

import com.yihu.cwd.controller.BaseController
import com.yihu.cwd.service.system.MenuService
import io.swagger.annotations.{ApiOperation, ApiParam, Api}
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.{RequestParam, RequestMethod, RequestMapping, RestController}

/**
  * Created by chenweida on 2017/3/28.
  */
@RestController
@RequestMapping(value = Array("/menu"))
@Api(description = "菜单")
class MenuController extends BaseController {
  @Autowired
  private var menuService: MenuService = _;

  @ApiOperation("新增菜单")
  @RequestMapping(value = Array("/add"), method = Array(RequestMethod.POST))
  def addMenu(
               @ApiParam(name = "name", value = "名称", required = true) @RequestParam(value = "name", required = true) name: String,
               @ApiParam(name = "url", value = "url", required = true) @RequestParam(value = "url", required = true) url: String,
               @ApiParam(name = "fid", value = "父id", required = true) @RequestParam(value = "fid", required = true) fid: String,
               @ApiParam(name = "remark", value = "备注", required = true) @RequestParam(value = "remark", required = true) remark: String): String = {
    try {
      menuService.add(name, url, remark,fid);
      success("新增成功")
    } catch {
      case e: Exception => {
        error(-1, e.getMessage)
      }
    }
  }
}
