package com.daocao.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

public class Report implements Serializable {
    private Integer id;

    private String informer;

    private Integer bookid;

    private String isaudit;

    private String note;

    @Override
    public String toString() {
        return "Report{" +
                "id=" + id +
                ", informer='" + informer + '\'' +
                ", bookid=" + bookid +
                ", isaudit='" + isaudit + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInformer() {
        return informer;
    }

    public void setInformer(String informer) {
        this.informer = informer;
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
}