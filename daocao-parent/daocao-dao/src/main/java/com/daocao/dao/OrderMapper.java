package com.daocao.dao;

import com.daocao.entity.Order;
import com.daocao.entity.OrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderMapper {
    int countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    List<Order> selectByExample(OrderExample example);

    Order selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    /**
     * 订单评价
     * @param order 评价内容
     * @return
     */
    int orderEvaluate(Order order);

    /**
     * 订单发货
     *
     * @param order 订单发货信息
     * @return
     */
    int orderSend(Order order);

    /**
     * 获取某本书籍的评价内容
     * @param id
     * @return
     */
    List<Order> getOrderEval(@Param("id") int id);
}