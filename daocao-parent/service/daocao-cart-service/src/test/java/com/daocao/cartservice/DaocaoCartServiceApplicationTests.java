package com.daocao.cartservice;

import com.daocao.CartService;
import com.daocao.entity.Cart;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class DaocaoCartServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    CartService cartService;

    @Test
    public void addGoodsTest(){
        List<Cart> redisCartList = cartService.getRedisCartList("123456");
        List<Cart> cartList = cartService.addGoodsToCart(redisCartList, 10, -2,"123456");
        cartService.addCartListToRedis("123456",cartList);
        System.out.println(cartList);
    }

}
