package com.daocao.pageweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author varg
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class DaocaoPageWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoPageWebApplication.class, args);
    }

}
