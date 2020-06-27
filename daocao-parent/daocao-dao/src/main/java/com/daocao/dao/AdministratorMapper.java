package com.daocao.dao;

import com.daocao.entity.Administrator;
import com.daocao.entity.AdministratorExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdministratorMapper {
    int countByExample(AdministratorExample example);

    int deleteByExample(AdministratorExample example);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    List<Administrator> selectByExample(AdministratorExample example);

    int updateByExampleSelective(@Param("record") Administrator record, @Param("example") AdministratorExample example);

    int updateByExample(@Param("record") Administrator record, @Param("example") AdministratorExample example);
}