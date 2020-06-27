package com.daocao.cartweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author varg
 */
@SpringBootApplication
@EnableDubbo
public class DaocaoCartWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoCartWebApplication.class, args);
    }

}
