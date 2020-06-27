package com.daocao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Order implements Serializable {

    private Integer id;

    private String userid;

    private String postcode;

    @NotBlank(message = "地址不能为空")
    private String address;

    @NotBlank(message = "电话不能为空")
    private String tel;

    @NotBlank(message = "联系人不能为空")
    private String contact;

    private BigDecimal postage;

    private BigDecimal amount;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date createdate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date paydate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date deliverydate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date overdate;

    private String logisticscompany;

    private String tracknumber;

    private String usernote;

    private String userevaluation;

    private String evltype;

    private String sellerid;

    private String ordertype;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode == null ? null : postcode.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact == null ? null : contact.trim();
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getCreatedate() {
        return createdate;
    }

    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }

    public Date getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(Date updatedate) {
        this.updatedate = updatedate;
    }

    public Date getPaydate() {
        return paydate;
    }

    public void setPaydate(Date paydate) {
        this.paydate = paydate;
    }

    public Date getDeliverydate() {
        return deliverydate;
    }

    public void setDeliverydate(Date deliverydate) {
        this.deliverydate = deliverydate;
    }

    public Date getOverdate() {
        return overdate;
    }

    public void setOverdate(Date overdate) {
        this.overdate = overdate;
    }

    public String getLogisticscompany() {
        return logisticscompany;
    }

    public void setLogisticscompany(String logisticscompany) {
        this.logisticscompany = logisticscompany == null ? null : logisticscompany.trim();
    }

    public String getTracknumber() {
        return tracknumber;
    }

    public void setTracknumber(String tracknumber) {
        this.tracknumber = tracknumber == null ? null : tracknumber.trim();
    }

    public String getUsernote() {
        return usernote;
    }

    public void setUsernote(String usernote) {
        this.usernote = usernote == null ? null : usernote.trim();
    }

    public String getUserevaluation() {
        return userevaluation;
    }

    public void setUserevaluation(String userevaluation) {
        this.userevaluation = userevaluation == null ? null : userevaluation.trim();
    }

    public String getEvltype() {
        return evltype;
    }

    public void setEvltype(String evltype) {
        this.evltype = evltype == null ? null : evltype.trim();
    }

    public String getSellerid() {
        return sellerid;
    }

    public void setSellerid(String sellerid) {
        this.sellerid = sellerid == null ? null : sellerid.trim();
    }

    public String getOrdertype() {
        return ordertype;
    }

    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype == null ? null : ordertype.trim();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", userid='" + userid + '\'' +
                ", postcode='" + postcode + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", contact='" + contact + '\'' +
                ", postage=" + postage +
                ", amount=" + amount +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", paydate=" + paydate +
                ", deliverydate=" + deliverydate +
                ", overdate=" + overdate +
                ", logisticscompany='" + logisticscompany + '\'' +
                ", tracknumber='" + tracknumber + '\'' +
                ", usernote='" + usernote + '\'' +
                ", userevaluation='" + userevaluation + '\'' +
                ", evltype='" + evltype + '\'' +
                ", sellerid='" + sellerid + '\'' +
                ", ordertype='" + ordertype + '\'' +
                '}';
    }
}