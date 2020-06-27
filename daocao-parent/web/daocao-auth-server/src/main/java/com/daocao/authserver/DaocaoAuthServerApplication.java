package com.daocao.authserver;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

/**
 * @author varg
 */
@SpringBootApplication
@EnableResourceServer
@EnableDubbo
public class DaocaoAuthServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoAuthServerApplication.class, args);
    }

}
