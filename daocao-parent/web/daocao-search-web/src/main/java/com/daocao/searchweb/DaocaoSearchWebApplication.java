package com.daocao.searchweb;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration;

/**
 * @author varg
 */
@SpringBootApplication(exclude = {RedisAutoConfiguration.class})
@EnableDubbo
public class DaocaoSearchWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoSearchWebApplication.class, args);
    }

}
