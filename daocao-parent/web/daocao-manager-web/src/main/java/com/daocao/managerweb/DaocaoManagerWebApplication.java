package com.daocao.managerweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author varg
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableDubbo
public class DaocaoManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoManagerWebApplication.class, args);
    }

}
