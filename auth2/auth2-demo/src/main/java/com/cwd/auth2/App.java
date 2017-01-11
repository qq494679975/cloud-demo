package com.cwd.auth2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * Created by Administrator on 2017/1/10.
 */
@SpringBootApplication
public class App {
    public static ApplicationContext ctx = null;

    public static void main(String[] args) {
        ctx = SpringApplication.run(App.class, args);
    }
}
