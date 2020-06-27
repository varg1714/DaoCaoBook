package com.daocao.entity;

import java.io.Serializable;

/**
 * @author varg
 * @date 2020/4/19 15:17
 */
public class SimpleAddress implements Serializable {

    private String username;
    private String tel;
    private String contact;
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SimpleAddress{" +
                "username='" + username + '\'' +
                ", tel='" + tel + '\'' +
                ", contact='" + contact + '\'' +
                ", address='" + address + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}
