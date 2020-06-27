package com.daocao.userweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Order;
import com.daocao.entity.OrderRecord;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.ResponseEntity;
import com.daocao.myentity.Result;
import com.daocao.orderinterface.OrderService;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author varg
 * @date 2020/4/19 22:03
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Reference
    private OrderService orderService;

    @GetMapping("/findOrders")
    public ResponseEntity<PageResult> findOrders(String status,int pageNum){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        System.out.println("username: "+username+" status: "+status);
        if("".equals(status) || "undefined".equals(status)){
            status = null;
        }
        return new ResponseEntity<>(true,"",orderService.findOrders(username, status,pageNum,5));
    }

    @PostMapping("/orderEvaluate")
    public ResponseEntity<String> orderEvaluate(@RequestBody Order order){
        order.setUserid(SecurityContextHolder.getContext().getAuthentication().getName());
        orderService.orderEvaluate(order);
        return new ResponseEntity<>(true,"评价成功");
    }

    @GetMapping("/findSellerOrder")
    public ResponseEntity<PageResult> findSellerOrder(String type,int pageNum){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PageResult result = orderService.findSellerOrder(username, type, pageNum, 5);
        return new ResponseEntity<>(true,"",result);
    }

    @PostMapping("/orderReceive")
    public ResponseEntity<String> orderReceive(int id){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        orderService.orderReceive(id,name);
        return new ResponseEntity<>(true,"订单已确认收货");
    }

    @PostMapping("/orderSend")
    public ResponseEntity<String> orderSend(@RequestBody Order order){
        order.setSellerid(SecurityContextHolder.getContext().getAuthentication().getName());
        orderService.orderSend(order);
        return new ResponseEntity<>(true,"订单已发货");
    }
}
