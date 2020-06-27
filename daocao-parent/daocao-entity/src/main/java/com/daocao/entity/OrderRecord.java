package com.daocao.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author varg
 * @date 2020/4/19 21:44
 */
@Data
@Builder
public class OrderRecord implements Serializable {

    private Order order;
    private List<OrderItem> orderItems;

    public OrderRecord(Order order, List<OrderItem> orderItems) {
        this.order = order;
        this.orderItems = orderItems;
    }

}
