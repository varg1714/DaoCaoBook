package com.daocao;

import com.daocao.entity.Book;
import com.daocao.entity.Cart;
import com.daocao.entity.SimpleAddress;

import java.util.List;

/**
 * @author varg
 * @date 2020/4/18 15:36
 */
public interface CartService {

    /**
     *
     *  添加商品到购物车
     * @param cartList 购物车列表
     * @param bookId    添加的商品图书ID
     * @param num   添加的商品图书数量
     * @param username   购买人
     * @return 添加完毕后的购物车数据
     */
    List<Cart> addGoodsToCart(List<Cart> cartList, int bookId,int num,String username);

    /**
     *  添加购物车列表到redis
     * @param username 登陆用户ID
     * @param cartList 购物车列表
     */
    void addCartListToRedis(String username,List<Cart> cartList);

    /**
     * 获取redis中存储的购物车信息
     * @param username 登陆用户ID
     * @return 购物车信息
     */
    List<Cart> getRedisCartList(String username);

    /**
     * 将准备购买的订单存储到redis
     * @param username 用户ID
     * @param orderList 待购买商品列表
     */
    void addOrderListToRedis(String username,List<Cart> orderList);

    /**
     * 从redis中取出待购买商品列表
     * @param username 用户ID
     * @return 待购买商品列表
     */
    List<Cart> getRedisOrderList(String username);

    /**
     * 保存订单
     * @param address 下单人信息
     */
    void saveOrder(SimpleAddress address);

}
