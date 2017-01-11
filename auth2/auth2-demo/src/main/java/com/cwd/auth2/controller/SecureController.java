package com.cwd.auth2.controller;

import com.cwd.auth2.entity.AccessToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import net.iharder.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author linaz
 * @created 2016.08.24 15:25
 * <p>
 * Spring Security logout handler
 */
@RestController
@RequestMapping("/token")
@Api(value = "token management", description = "token管理，用户访问API必须要有此token" ,tags = "")
public class SecureController {

    @Value("${server.port}")
    int port;

    @Autowired
    ObjectMapper objectMapper;

    //curl -u admin:admin 'http://localhost:8091/oauth/token?username=admin&password=admin&grant_type=password'
    //URL url = new URL("http://localhost:8090/oauth/token?username=admin&password=admin&grant_type=password");
    @RequestMapping(value = "/tokens", method = RequestMethod.GET)
    @ApiOperation(value = "获取token", notes = "输入用户名和密码来获取token，过期时间为一个月")
    public AccessToken getToken(
            @ApiParam(name = "user_name", value = "用户名", defaultValue = "admin")
            @RequestParam(value = "user_name") String userName,
            @ApiParam(name = "password", value = "密码", defaultValue = "admin")
            @RequestParam(value = "password") String password) throws Exception {

        URL url = new URL("http://localhost:"
                + port + "/oauth/token?username="
                + userName + "&password="
                + password + "&grant_type=password");

        String encoding = Base64.encodeBytes(("admin" + ":" + "admin").getBytes());

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setRequestProperty("Authorization", "Basic " + encoding);
        InputStream content = connection.getInputStream();
        BufferedReader in = new BufferedReader(new InputStreamReader(content));
        String line;
        AccessToken accessToken = new AccessToken();
        while ((line = in.readLine()) != null) {
            accessToken = objectMapper.readValue(line, AccessToken.class);
        }
        return accessToken;
    }


}
