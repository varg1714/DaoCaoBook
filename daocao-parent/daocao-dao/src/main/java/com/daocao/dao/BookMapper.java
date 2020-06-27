package com.daocao.dao;

import com.daocao.entity.Book;
import com.daocao.entity.BookExample;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BookMapper {
    int countByExample(BookExample example);

    int deleteByExample(BookExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    int insertSelective(Book record);

    List<Book> selectByExample(BookExample example);

    Book selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Book record, @Param("example") BookExample example);

    int updateByExample(@Param("record") Book record, @Param("example") BookExample example);

    int updateByPrimaryKeySelective(Book record);

    int updateByPrimaryKey(Book record);

    /**
     * 减少数据库中商品余量
     *
     * @param id    书籍ID
     * @param count 新的书籍数量
     * @return
     */
    int decreBookCount(@Param("id") int id, @Param("count") int count);

    /**
     * 更新书籍的基本信息
     * @param book
     * @return
     */
    int updateBaseInfo(Book book);
}