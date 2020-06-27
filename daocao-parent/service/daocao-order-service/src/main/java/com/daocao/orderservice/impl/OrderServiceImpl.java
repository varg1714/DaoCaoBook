package com.daocao.orderservice.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.BookMapper;
import com.daocao.dao.OrderItemMapper;
import com.daocao.dao.OrderMapper;
import com.daocao.entity.*;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.Result;
import com.daocao.orderinterface.OrderService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    @Resource
    private BookMapper bookMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Order> findAll() {
        return orderMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> page = (Page<Order>) orderMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Order order) {
        orderMapper.insert(order);
    }


    /**
     * 修改
     */
    @Override
    public void update(Order order) {
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Order findOne(Integer id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            orderMapper.deleteByPrimaryKey(id);
        }
    }

    @Override
    public PageResult findPage(Order order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();

        if (order != null) {
            if (order.getUserid() != null && order.getUserid().length() > 0) {
                criteria.andUseridEqualTo("%" + order.getUserid() + "%");
            }
            if (order.getPostcode() != null && order.getPostcode().length() > 0) {
                criteria.andPostcodeLike("%" + order.getPostcode() + "%");
            }
            if (order.getAddress() != null && order.getAddress().length() > 0) {
                criteria.andAddressLike("%" + order.getAddress() + "%");
            }
            if (order.getTel() != null && order.getTel().length() > 0) {
                criteria.andTelLike("%" + order.getTel() + "%");
            }
            if (order.getContact() != null && order.getContact().length() > 0) {
                criteria.andContactLike("%" + order.getContact() + "%");
            }
            if (order.getLogisticscompany() != null && order.getLogisticscompany().length() > 0) {
                criteria.andLogisticscompanyLike("%" + order.getLogisticscompany() + "%");
            }
            if (order.getTracknumber() != null && order.getTracknumber().length() > 0) {
                criteria.andTracknumberLike("%" + order.getTracknumber() + "%");
            }
            if (order.getUsernote() != null && order.getUsernote().length() > 0) {
                criteria.andUsernoteLike("%" + order.getUsernote() + "%");
            }
            if (order.getUserevaluation() != null && order.getUserevaluation().length() > 0) {
                criteria.andUserevaluationLike("%" + order.getUserevaluation() + "%");
            }
            if (order.getEvltype() != null && order.getEvltype().length() > 0) {
                criteria.andEvltypeLike("%" + order.getEvltype() + "%");
            }
            if (order.getSellerid() != null && order.getSellerid().length() > 0) {
                criteria.andSelleridEqualTo("%" + order.getSellerid() + "%");
            }
            if (order.getOrdertype() != null && order.getOrdertype().length() > 0) {
                criteria.andOrdertypeEqualTo("%" + order.getOrdertype() + "%");
            }

        }

        Page<Order> page = (Page<Order>) orderMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void saveOrder(SimpleAddress address, List<Cart> orderList) {

        for (Cart cart : orderList) {

            for (OrderItem orderItem : cart.getOrderItems()) {
                Book book = bookMapper.selectByPrimaryKey(orderItem.getGoodsid());
                if ("0".equals(book.getIssell())
                        || "0".equals(book.getIsaudit())
                        || book.getNumber() < orderItem.getGoodscount()) {
                    throw new RuntimeException("购买商品不存在或数量超过库存量");
                }
            }
            // 初始化订单信息
            Order order = initOrder(address, cart);
            orderMapper.insert(order);
            Integer id = order.getId();
            for (OrderItem orderItem : cart.getOrderItems()) {
                orderItem.setOrderid(id);
                orderItemMapper.insertSelective(orderItem);
                Book book = bookMapper.selectByPrimaryKey(orderItem.getGoodsid());
                bookMapper.decreBookCount(orderItem.getGoodsid(), book.getNumber() - orderItem.getGoodscount());
            }

        }
    }

    @Override
    public PageResult findOrders(String username, String status, int pageNum, int pageSize) {
        List<OrderRecord> recordList = new ArrayList<>();

        // 获取该用户所有订单
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(username);

        // 设置订单状态
        if (status != null) {
            criteria.andOrdertypeEqualTo(status);
        }

        // 分页查询订单
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> orders = (Page<Order>) orderMapper.selectByExample(example);

        // 获取该订单所购买的商品
        recordList = findOrderItem(orders, recordList);

        return new PageResult(orders.getTotal(), recordList, (long) orders.getPages());
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int orderEvaluate(Order order) {
        order.setOverdate(new Date());
        return orderMapper.orderEvaluate(order);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int orderReceive(int id, String username) {
        // 将订单设为收货状态
        Order order = new Order();
        order.setOrdertype("3");

        // 前提为订单已发货
        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andUseridEqualTo(username);
        criteria.andOrdertypeEqualTo("2");

        return orderMapper.updateByExampleSelective(order, example);
    }

    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public int orderSend(Order order) {
        return orderMapper.orderSend(order);
    }

    @Override
    public PageResult findSellerOrder(String username, String type, int pageNum, int pageSize) {

        List<OrderRecord> recordList = new ArrayList<>();

        OrderExample example = new OrderExample();
        OrderExample.Criteria criteria = example.createCriteria();
        criteria.andSelleridEqualTo(username);
        criteria.andOrdertypeEqualTo(type);

        // 分页查询
        PageHelper.startPage(pageNum, pageSize);
        Page<Order> orders = (Page<Order>) orderMapper.selectByExample(example);

        // 获取该订单所购买的商品
        recordList = findOrderItem(orders, recordList);

        return new PageResult(orders.getTotal(), recordList, (long) orders.getPages());
    }

    public Order initOrder(SimpleAddress address, Cart cart) {
        // 初始化订单信息
        Date date = new Date();
        Order order = new Order();

        order.setUserid(address.getUsername());
        order.setAddress(address.getAddress());
        order.setTel(address.getTel());
        order.setContact(address.getContact());
        order.setPostage(BigDecimal.valueOf(cart.getPostage()));
        order.setAmount(BigDecimal.valueOf(cart.getTotalMoney()));
        order.setCreatedate(date);
        order.setUpdatedate(date);
        order.setPaydate(date);
        order.setSellerid(cart.getSellerId());
        order.setOrdertype("1");
        return order;
    }

    /**
     * 获取订单下的购物明细
     *
     * @param orders     订单集
     * @param recordList 订单结果集
     * @return 订单结果集
     */
    public List<OrderRecord> findOrderItem(Page<Order> orders, List<OrderRecord> recordList) {
        for (Order order : orders.getResult()) {
            OrderItemExample itemExample = new OrderItemExample();
            OrderItemExample.Criteria itemExampleCriteria = itemExample.createCriteria();
            itemExampleCriteria.andOrderidEqualTo(order.getId());
            List<OrderItem> orderItems = orderItemMapper.selectByExample(itemExample);

            recordList.add(new OrderRecord(order, orderItems));
        }
        return recordList;
    }

}
