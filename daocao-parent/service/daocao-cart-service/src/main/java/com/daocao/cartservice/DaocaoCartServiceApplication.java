package com.daocao.cartservice;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.Semaphore;

/**
 * @author varg
 */
@SpringBootApplication
@MapperScan("com.daocao.dao")
@EnableDubbo
public class DaocaoCartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DaocaoCartServiceApplication.class, args);
        Semaphore semaphore = new Semaphore(0);
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
