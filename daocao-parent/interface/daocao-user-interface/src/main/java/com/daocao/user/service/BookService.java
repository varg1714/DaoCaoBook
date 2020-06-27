package com.daocao.user.service;

import com.daocao.entity.Book;
import com.daocao.entity.BookEvaluation;
import com.daocao.myentity.PageResult;

import java.util.List;
import java.util.Map;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface BookService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<Book> findAll();

    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);

    /**
     * 增加
     */
    void add(Book book);

    /**
     * 修改
     */
    void update(Book book);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    Book findOne(Integer id, String username);

    Book findOne(Integer id);

    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Integer[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(Book book, int pageNum, int pageSize);

    /**
     * 获取用户出售中的书籍(分页查询)
     *
     * @param username  出售人
     * @param isSellOut 是否售空
     * @param pageNum   当前页
     * @param pageSize  页大小
     * @return
     */
    PageResult findSellingBook(String username, boolean isSellOut, int pageNum, int pageSize);

    /**
     * 补充商品库存存货
     *
     * @param id     书籍ID
     * @param number 补充数量
     * @param seller 出售人
     * @return
     */
    int refillBook(int id, int number, String seller);

    /**
     * 更新书籍的基本信息
     *
     * @param book
     * @return
     */
    int updateBaseInfo(Book book);

    /**
     * 获取对某本书籍的订单评价
     * @param id 书籍ID
     * @param pageNum 当前查询页
     * @param pageSize 页面大小
     * @return
     */
    PageResult getBookEvaluation(int id,int pageNum,int pageSize);

    /**
     * 获取推荐最新书籍(通过分页查询数据库中最新记录获得)
     * @param pageNum 页码
     * @param pageSize 每页大小
     * @return
     */
    PageResult getRecommendBooks(int pageNum,int pageSize);
}
