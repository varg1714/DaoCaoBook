package com.daocao.pageweb.listener;

import com.daocao.pageweb.service.BookHtmlService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author varg
 * @date 2020/5/6 20:57
 *
 * 负责书籍静态化页面的生成与清理
 *
 */
@RabbitListener(queues = "bookGeneQueue")
@Component
public class BookGeneListener {

    @Autowired
    private BookHtmlService bookHtmlService;

    @RabbitHandler
    public void bookHtmlGene(Integer[] ids){
        try {
            bookHtmlService.geneHtml(ids);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
