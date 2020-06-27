package com.daocao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Account implements Serializable {
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;

    @Email
    private String email;

    @NotBlank(message = "手机号不能为空")
    private String tel;

    private Date createdate;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date updatedate;

    @NotBlank(message = "昵称不能为空")
    private String nickname;

    private String name;

    private String sex;

    private String status;

    private String headpic;

    private BigDecimal balance;

    private String telchecked;

    private String emailchecked;

    private String userlevel;

    private Date lastlogindate;

    private Integer goodreputationnum;

    private Integer badreputationnum;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel == null ? null : tel.trim();
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getHeadpic() {
        return headpic;
    }

    public void setHeadpic(String headpic) {
        this.headpic = headpic == null ? null : headpic.trim();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getTelchecked() {
        return telchecked;
    }

    public void setTelchecked(String telchecked) {
        this.telchecked = telchecked == null ? null : telchecked.trim();
    }

    public String getEmailchecked() {
        return emailchecked;
    }

    public void setEmailchecked(String emailchecked) {
        this.emailchecked = emailchecked == null ? null : emailchecked.trim();
    }

    public String getUserlevel() {
        return userlevel;
    }

    public void setUserlevel(String userlevel) {
        this.userlevel = userlevel == null ? null : userlevel.trim();
    }

    public Date getLastlogindate() {
        return lastlogindate;
    }

    public void setLastlogindate(Date lastlogindate) {
        this.lastlogindate = lastlogindate;
    }

    public Integer getGoodreputationnum() {
        return goodreputationnum;
    }

    public void setGoodreputationnum(Integer goodreputationnum) {
        this.goodreputationnum = goodreputationnum;
    }

    public Integer getBadreputationnum() {
        return badreputationnum;
    }

    public void setBadreputationnum(Integer badreputationnum) {
        this.badreputationnum = badreputationnum;
    }

    @Override
    public String toString() {
        return "Account{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", tel='" + tel + '\'' +
                ", createdate=" + createdate +
                ", updatedate=" + updatedate +
                ", nickname='" + nickname + '\'' +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                ", status='" + status + '\'' +
                ", headpic='" + headpic + '\'' +
                ", balance=" + balance +
                ", telchecked='" + telchecked + '\'' +
                ", emailchecked='" + emailchecked + '\'' +
                ", userlevel='" + userlevel + '\'' +
                ", lastlogindate=" + lastlogindate +
                ", goodreputationnum=" + goodreputationnum +
                ", badreputationnum=" + badreputationnum +
                '}';
    }
}