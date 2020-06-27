package com.daocao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

public class OrderItem implements Serializable {
    private Integer id;

    private Integer orderid;

    private Integer goodsid;

    private Integer goodscount;

    private BigDecimal price;

    private BigDecimal totalmoney;

    private String goodsname;

    private String goodsimage;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderid() {
        return orderid;
    }

    public void setOrderid(Integer orderid) {
        this.orderid = orderid;
    }

    public Integer getGoodsid() {
        return goodsid;
    }

    public void setGoodsid(Integer goodsid) {
        this.goodsid = goodsid;
    }

    public Integer getGoodscount() {
        return goodscount;
    }

    public void setGoodscount(Integer goodscount) {
        this.goodscount = goodscount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(BigDecimal totalmoney) {
        this.totalmoney = totalmoney;
    }

    public String getGoodsname() {
        return goodsname;
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname;
    }

    public String getGoodsimage() {
        return goodsimage;
    }

    public void setGoodsimage(String goodsimage) {
        this.goodsimage = goodsimage;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "id=" + id +
                ", orderid=" + orderid +
                ", goodsid=" + goodsid +
                ", goodscount=" + goodscount +
                ", price=" + price +
                ", totalmoney=" + totalmoney +
                ", goodsname='" + goodsname + '\'' +
                ", goodsimage='" + goodsimage + '\'' +
                '}';
    }
}