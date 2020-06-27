package com.daocao.entity;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class Category implements Serializable {
    private Integer id;

    private String name;

    @Min(value = 0,message = "分类所属父类非法")
    private Integer parentid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentid() {
        return parentid;
    }

    public void setParentid(Integer parentid) {
        this.parentid = parentid;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parentid=" + parentid +
                '}';
    }
}