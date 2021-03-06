package com.cwd.auth2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/22.
 * 登陆的控制器
 */
@Controller
@RequestMapping(value = "/page")
public class LoginController {

    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        System.out.println("跳入登陆页面");
        return "/login.ftl";
    }

    @RequestMapping(value = "/errorPage", method = RequestMethod.POST)
    public String errorPage() {
        System.out.println("失败页面");

        return "/error.ftl";
    }

    @RequestMapping(value = "/indexPage", method = RequestMethod.POST)
    public String indexPage() {
        System.out.println("成功页面");

        return "/index.ftl";
    }


    @RequestMapping(value = "/menuPage", method = RequestMethod.GET)
    public String menuPage() {
        System.out.println("menuPage页面");

        return "/menu.ftl";
    }

    @RequestMapping("/oauthApproval")
    public ModelAndView oauthApprovalPage(Map<String, Object> model, HttpServletRequest request) throws Exception {
        System.out.println("授权页面");
        if (request.getAttribute("_csrf") != null) {
            model.put("_csrf", request.getAttribute("_csrf"));
        } else {
            model.put("_csrf", "");
        }

        String scopes[] = request.getParameter("scope") == null ? new String[0] : request.getParameter("scope").split("\\+");
        model.put("scopes", scopes);

        return new ModelAndView("/oauth_approval.ftl", model);
    }
}
