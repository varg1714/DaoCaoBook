package com.daocao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class SellAudit implements Serializable {
    private Integer id;

    private Integer bookid;

    private String isaudit;

    private String note;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookid() {
        return bookid;
    }

    public void setBookid(Integer bookid) {
        this.bookid = bookid;
    }

    public String getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "SellAudit{" +
                "id=" + id +
                ", bookid=" + bookid +
                ", isaudit='" + isaudit + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}