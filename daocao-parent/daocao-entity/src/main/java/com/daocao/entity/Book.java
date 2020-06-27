package com.daocao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Book implements Serializable {

    private Integer id;

    private String author;

    private String publisher;

    private String version;

    private String isbn;

    private BigDecimal publishprice;

    @NotBlank(message = "书籍名称不能为空")
    private String name;

    @JsonFormat(shape=JsonFormat.Shape.STRING,pattern="yyyy-MM",timezone="GMT+8")
    private Date publishdate;

    @DecimalMin(value = "0.0",message = "销售价格必须大于0")
    private BigDecimal sellprice;

    @Length(min = 1,max = 16,message = "装订类型非法")
    private String bindtype;

    @Length(min = 0,max = 255,message = "书籍描述最多255个字哦")
    private String description;

    @DecimalMin(value = "0.0",message = "邮费必须大于等于0")
    private BigDecimal postage;

    @Min(value = 1,message = "书籍数量必须大于0")
    private Integer number;

    @NotNull(message = "书籍类型1必选")
    private Integer booktype1;

    private Integer pagenumber;

    private String papersize;

    private Integer booktype2;

    private String seller;

    private String issell;

    private String bookimages;

    private String isaudit;

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
        this.name = name == null ? null : name.trim();
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author == null ? null : author.trim();
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher == null ? null : publisher.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn == null ? null : isbn.trim();
    }

    public BigDecimal getPublishprice() {
        return publishprice;
    }

    public void setPublishprice(BigDecimal publishprice) {
        this.publishprice = publishprice;
    }

    public BigDecimal getSellprice() {
        return sellprice;
    }

    public void setSellprice(BigDecimal sellprice) {
        this.sellprice = sellprice;
    }

    public String getBindtype() {
        return bindtype;
    }

    public void setBindtype(String bindtype) {
        this.bindtype = bindtype == null ? null : bindtype.trim();
    }

    public String getPapersize() {
        return papersize;
    }

    public void setPapersize(String papersize) {
        this.papersize = papersize == null ? null : papersize.trim();
    }

    public Integer getPagenumber() {
        return pagenumber;
    }

    public void setPagenumber(Integer pagenumber) {
        this.pagenumber = pagenumber;
    }

    public Integer getBooktype1() {
        return booktype1;
    }

    public void setBooktype1(Integer booktype1) {
        this.booktype1 = booktype1;
    }

    public Integer getBooktype2() {
        return booktype2;
    }

    public void setBooktype2(Integer booktype2) {
        this.booktype2 = booktype2;
    }

    public String getSeller() {
        return seller;
    }

    public void setSeller(String seller) {
        this.seller = seller == null ? null : seller.trim();
    }

    public String getIssell() {
        return issell;
    }

    public void setIssell(String issell) {
        this.issell = issell == null ? null : issell.trim();
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBookimages() {
        return bookimages;
    }

    public void setBookimages(String bookimages) {
        this.bookimages = bookimages == null ? null : bookimages.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public BigDecimal getPostage() {
        return postage;
    }

    public void setPostage(BigDecimal postage) {
        this.postage = postage;
    }

    public String getIsaudit() {
        return isaudit;
    }

    public void setIsaudit(String isaudit) {
        this.isaudit = isaudit == null ? null : isaudit.trim();
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publishdate=" + publishdate +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", version='" + version + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishprice=" + publishprice +
                ", sellprice=" + sellprice +
                ", bindtype='" + bindtype + '\'' +
                ", papersize='" + papersize + '\'' +
                ", pagenumber=" + pagenumber +
                ", booktype1=" + booktype1 +
                ", booktype2=" + booktype2 +
                ", seller='" + seller + '\'' +
                ", issell='" + issell + '\'' +
                ", number=" + number +
                ", bookimages='" + bookimages + '\'' +
                ", description='" + description + '\'' +
                ", postage=" + postage +
                ", isaudit='" + isaudit + '\'' +
                '}';
    }
}