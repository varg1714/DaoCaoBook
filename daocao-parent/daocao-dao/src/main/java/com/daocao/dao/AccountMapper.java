package com.daocao.dao;

import com.daocao.entity.Account;
import com.daocao.entity.AccountExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AccountMapper {
    int countByExample(AccountExample example);

    int deleteByExample(AccountExample example);

    int deleteByPrimaryKey(String username);

    int insert(Account record);

    int insertSelective(Account record);

    List<Account> selectByExample(AccountExample example);

    Account selectByPrimaryKey(String username);

    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    int updateByPrimaryKeySelective(Account record);

    int updateByPrimaryKey(Account record);

    /**
     * 更新用户的基本信息，包含昵称 真实姓名 手机号 邮箱 头像地址等信息。
     * 此修改不会涉及到账户余额以及密码等隐私信息的更改。
     * @param account 需更新信息的账户
     * @return
     */
    int updateBaseInfo(Account account);
}