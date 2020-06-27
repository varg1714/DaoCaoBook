package com.daocao.pageweb.listener;

import com.daocao.pageweb.service.BookHtmlService;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author varg
 * @date 2020/5/6 22:02
 */
@RabbitListener(queues = "bookDeleteQueue")
@Component
public class BookDeleListener {

    @Autowired
    private BookHtmlService bookHtmlService;

    @RabbitHandler
    public void deleBook(Integer[] ids){
        bookHtmlService.deleHtme(ids);
    }

}
