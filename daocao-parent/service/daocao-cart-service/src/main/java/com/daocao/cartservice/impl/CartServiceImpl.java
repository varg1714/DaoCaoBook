package com.daocao.cartservice.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.CartService;
import com.daocao.dao.AccountMapper;
import com.daocao.dao.BookMapper;
import com.daocao.entity.Book;
import com.daocao.entity.Cart;
import com.daocao.entity.OrderItem;
import com.daocao.entity.SimpleAddress;
import com.daocao.orderinterface.OrderService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * @author varg
 * @date 2020/4/18 16:15
 */

@Service
public class CartServiceImpl implements CartService {

    @Resource
    BookMapper bookMapper;

    @Resource
    AccountMapper accountMapper;

    @Reference
    OrderService orderService;

    @Resource
    RedisTemplate<String, List<Cart>> redisTemplate;

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public List<Cart> addGoodsToCart(List<Cart> cartList, int bookId, int num, String username) {

        Book book = bookMapper.selectByPrimaryKey(bookId);
        if (book == null || "0".equals(book.getIssell())) {
            throw new IllegalArgumentException("不存在该商品");
        } else if (num > book.getNumber()) {
            throw new IllegalArgumentException("添加商品数量超过在售数量");
        } else if (book.getSeller().equals(username)) {
            throw new IllegalArgumentException("您不能购买自己出售的商品哦");
        }

        String sellerId = book.getSeller();
        String sellerName = accountMapper.selectByPrimaryKey(sellerId).getNickname();

        // 获取购物车列表中包含该商品的出售人
        Cart seller = searchSeller(cartList, sellerId);
        if (seller == null) {
            // 不存在该商家，创建该商家
            seller = new Cart();
            seller.setSellerId(sellerId);
            seller.setSellerName(sellerName);
            seller.setOrderItems(new ArrayList<>());
            seller.setPostage((double) 0L);
            cartList.add(seller);
        }

        // 获取购物车中该出售人所出售的商品
        List<OrderItem> orderItems = seller.getOrderItems();
        OrderItem orderItem = searchBook(orderItems, bookId);
        if (orderItem == null) {
            // 未出售该商品，添加该商品到购物车中
            orderItem = new OrderItem();
            orderItem.setGoodsid(bookId);
            orderItem.setGoodscount(num);
            orderItem.setGoodsimage(book.getBookimages());
            orderItem.setGoodsname(book.getName());
            orderItem.setPrice(book.getSellprice());

            BigDecimal itemMoney = orderItem.getPrice().multiply(new BigDecimal(orderItem.getGoodscount()));
            orderItem.setTotalmoney(itemMoney);

            // 在购物车列表中更新该商家信息
            orderItems.add(orderItem);
            seller.setTotalNum(seller.getTotalNum() + num);
            Double totalMoney = seller.getTotalMoney();
            totalMoney = itemMoney.add(new BigDecimal(totalMoney)).setScale(2, RoundingMode.HALF_UP).doubleValue();
            seller.setTotalMoney(totalMoney);
            seller.setPostage(book.getPostage().add(BigDecimal.valueOf(seller.getPostage())).doubleValue());
        } else {
            // 购物车中已有该商品信息，更新该商品信息

            // 若是减少商品数量到0
            if (orderItem.getGoodscount() + num <= 0) {
                // 商品数量减少到0
                orderItems.remove(orderItem);
                seller.setTotalNum(seller.getTotalNum() - orderItem.getGoodscount());

                Double totalMoney = seller.getTotalMoney();
                totalMoney = BigDecimal.valueOf(totalMoney).subtract(orderItem.getTotalmoney())
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();
                seller.setTotalMoney(totalMoney);

                // 如果删除该商品后购物车中再无该出售人商品，则移除该商家
                if (orderItems.size() == 0) {
                    cartList.remove(seller);
                }

            } else {

                // 在该商家商品中修改出售商品信息
                orderItem.setGoodscount(orderItem.getGoodscount() + num);
                orderItem.setTotalmoney(orderItem.getPrice().multiply(BigDecimal.valueOf(orderItem.getGoodscount())));

                seller.setTotalNum(seller.getTotalNum() + num);
                Double totalMoney = seller.getTotalMoney();
                totalMoney = BigDecimal.valueOf(totalMoney).add(orderItem.getPrice().multiply(BigDecimal.valueOf(num)))
                        .setScale(2, RoundingMode.HALF_UP).doubleValue();
                seller.setTotalMoney(totalMoney);
            }
        }

        return cartList;
    }

    /**
     * 寻找购物车列表中是否有对应出售人
     *
     * @param cartList 购物车列表
     * @param sellerId 出售人ID
     * @return 查找到的商家，未找到返回null
     */
    public Cart searchSeller(List<Cart> cartList, String sellerId) {

        for (Cart cart : cartList) {
            if (cart.getSellerId().equals(sellerId)) {
                return cart;
            }
        }
        return null;
    }

    /**
     * 查找购物车中是否有该商品
     *
     * @param orderItemList 购物车列表
     * @param bookId        商品ID
     * @return 查找到的商品，未查找到则返回null
     */
    public OrderItem searchBook(List<OrderItem> orderItemList, int bookId) {
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getGoodsid() == bookId) {
                return orderItem;
            }
        }
        return null;
    }

    /**
     * 将购物车商品添加到redis
     *
     * @param username 登陆用户ID
     * @param cartList 购物车列表
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addCartListToRedis(String username, List<Cart> cartList) {
        redisTemplate.boundHashOps("cartList").put(username, cartList);
    }

    @Override
    public List<Cart> getRedisCartList(String username) {
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
        if (cartList == null) {
            cartList = new ArrayList<>();
        }
        return cartList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addOrderListToRedis(String username, List<Cart> orderList) {
        redisTemplate.boundHashOps("orderList").put(username, orderList);
    }

    @Override
    public List<Cart> getRedisOrderList(String username) {
        List<Cart> orderList = (List<Cart>) redisTemplate.boundHashOps("orderList").get(username);
        if (orderList == null) {
            orderList = new ArrayList<>();
        }
        return orderList;
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrder(SimpleAddress address) {
        String username = address.getUsername();
        // 保存订单到数据库
        List<Cart> redisOrderList = this.getRedisOrderList(username);
        orderService.saveOrder(address, redisOrderList);

        // 清除购物车中已被购买的商品
        List<Cart> redisCartList = this.getRedisCartList(username);
        for (Cart cart : redisOrderList) {
            List<OrderItem> orderItems = cart.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                redisCartList = this.addGoodsToCart(redisCartList, orderItem.getGoodsid(), -orderItem.getGoodscount(), address.getUsername());
            }
        }
        this.addCartListToRedis(username, redisCartList);
        // 清除redis中已下单的数据
        redisTemplate.boundHashOps("orderList").delete(username);
    }
}
