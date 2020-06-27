package com.daocao.cartweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.CartService;
import com.daocao.entity.Cart;
import com.daocao.entity.SimpleAddress;
import com.daocao.myentity.Result;
import org.springframework.boot.actuate.endpoint.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author varg
 * @date 2020/4/18 17:51
 */
@RestController
@RequestMapping("/cart")
public class CartController {

    @Reference
    CartService cartService;

    @GetMapping("/addGoods")
    @CrossOrigin(origins = "http://localhost:8083", allowCredentials = "true")
    public Result addGoods(int bookId, int num) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null || "anonymousUser".equals(name)) {
            return new Result(false, "您还没有登陆哦,请登陆以后再尝试吧!");
        }
        List<Cart> redisCartList = cartService.getRedisCartList(name);
        List<Cart> cartList = cartService.addGoodsToCart(redisCartList, bookId, num,name);
        cartService.addCartListToRedis(name, cartList);
        return new Result(true, "添加成功,您可以到购物车页面进行结算哟!");
    }

    @GetMapping("/getCartGoods")
    public List<Cart> getCartGoods() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartService.getRedisCartList(name);
    }

    @PostMapping("/addOrderList")
    public Result addOrderList(@RequestBody List<Map<String, Integer>> orderList) {

        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        if (name == null) {
            return new Result(false, "您还没有登陆哦,请登陆以后再尝试吧!");
        }
        List<Cart> redisOrderList = cartService.getRedisOrderList(name);
        if (redisOrderList.size() > 0) {
            return new Result(false,"请将上次未确定的订单结算或取消后再试试吧!");
        }

        // 将传入订单的商品数据全部添加到待付款订单
        for (Map<String, Integer> order : orderList) {
            redisOrderList = cartService.addGoodsToCart(redisOrderList, order.get("id"), order.get("num"),name);
        }
        cartService.addOrderListToRedis(name, redisOrderList);
        return new Result(true, "添加成功，您可以到结算页面进行结算了哟!");
    }

    @GetMapping("/getOrderList")
    public List<Cart> getOrderList(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return cartService.getRedisOrderList(name);
    }

    @DeleteMapping("/deleteOrderList")
    public Result deleteOrderList(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Cart> redisOrderList = cartService.getRedisOrderList(name);
        redisOrderList.removeAll(redisOrderList);
        cartService.addOrderListToRedis(name,redisOrderList);
        return new Result(true,"删除成功");
    }

    @PostMapping("/saveOrder")
    public Result saveOrder(@RequestBody SimpleAddress address){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setUsername(name);
        cartService.saveOrder(address);
        return new Result(true,"购买成功,请等待卖家为您发货吧!");
    }
}
