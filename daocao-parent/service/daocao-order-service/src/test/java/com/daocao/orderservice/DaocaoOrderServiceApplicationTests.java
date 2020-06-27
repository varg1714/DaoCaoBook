package com.daocao.orderservice;

import com.daocao.dao.OrderMapper;
import com.daocao.entity.Order;
import com.daocao.orderservice.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;

@SpringBootTest
class DaocaoOrderServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    OrderServiceImpl orderService;

    @Resource
    OrderMapper orderMapper;

    @Test
    public void orderInsertTest(){
        Date date =  new Date();
        Order order = new Order();

        order.setUserid("123456");
        order.setAddress("海南省海口市");
        order.setTel("16543154");
        order.setContact("王刚");
        order.setPostage(BigDecimal.valueOf(0));
        order.setAmount(new BigDecimal("35.8"));
        order.setCreatedate(date);
        order.setUpdatedate(date);
        order.setPaydate(date);
        order.setSellerid("123456");
        order.setOrdertype("1");

        int id = orderMapper.insertSelective(order);
        System.out.println("id:"+order.getId());
    }

    @Test
    public void ordersFindTest(){
        orderService.findOrders("123456","1",1,5);
    }

    @Test
    public void orderEvalTest(){
        Order order = new Order();
        order.setEvltype("5");
        order.setUserevaluation("这个商品好好啊");
        order.setId(7);
        order.setUserid("123456");
        orderService.orderEvaluate(order);
    }

    @Test
    public void orderReceiveTest(){
        orderService.orderReceive(6,"123456");
    }

    @Test
    public void orderSendTest(){
        Order order = new Order();
        order.setLogisticscompany("顺丰速运");
        order.setTracknumber("785412665412541");
        order.setId(9);
        order.setSellerid("123456");
        orderService.orderSend(order);
    }
}
