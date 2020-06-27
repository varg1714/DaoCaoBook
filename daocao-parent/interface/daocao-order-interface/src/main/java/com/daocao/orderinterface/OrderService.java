package com.daocao.orderinterface;

import com.daocao.entity.Cart;
import com.daocao.entity.Order;
import com.daocao.entity.OrderRecord;
import com.daocao.entity.SimpleAddress;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.Result;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface OrderService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<Order> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(Order order);


    /**
     * 修改
     */
    void update(Order order);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    Order findOne(Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Integer[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(Order order, int pageNum, int pageSize);

    /**
     * 保存订单列表的商品
     *
     * @param orderList
     * @return
     */
    void saveOrder(SimpleAddress address, List<Cart> orderList);


    /**
     * 获取该用户指定状态的订单
     *
     * @param username 用户ID
     * @param status   订单状态
     * @return
     */
    PageResult findOrders(String username, String status, int pageNum, int pageSize);

    /**
     * 订单评价
     *
     * @param order 评价内容
     * @return
     */
    int orderEvaluate(Order order);

    /**
     * 订单收货功能：用户确认订单已送达
     *
     * @param id       订单ID
     * @param username 订单所属人
     * @return
     */
    int orderReceive(int id, String username);

    /**
     * 订单发货
     *
     * @param order 订单发货信息
     * @return
     */
    int orderSend(Order order);

    /**
     * 获取用户出售中不同状态的订单
     *
     * @param username 出售人
     * @param type     订单类型
     * @param pageNum  当前页数
     * @param pageSize 页面数据量
     * @return
     */
    PageResult findSellerOrder(String username, String type, int pageNum, int pageSize);
}
