package com.daocao.userweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author varg
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class DaocaoUserWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoUserWebApplication.class, args);
    }

}
