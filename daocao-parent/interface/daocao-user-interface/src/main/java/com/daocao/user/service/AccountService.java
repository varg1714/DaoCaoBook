package com.daocao.user.service;

import com.daocao.entity.Account;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.ResponseEntity;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface AccountService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<Account> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(Account account);


    /**
     * 修改
     */
    void update(Account account);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    Account findOne(String id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(String[] ids);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(Account account, int pageNum, int pageSize);

    /**
     * 修改用户基本信息
     *
     * @param account
     * @return 更新结果
     */
    int updateBaseInfo(Account account);

    /**
     * 发送短信验证码
     *
     * @param tel 用户手机号
     * @return
     */
    boolean sendTelCode(String tel);

	/**
	 * 验证用户输入的短信验证码
	 * @param tel 用户手机号
	 * @param code 验证码
	 * @return
	 */
	boolean checkTelCode(String tel,String code);

    /**
     * 重新设置用户密码
     * @param account 待设置密码的账户
     * @param checkCode 验证码
     * @return
     */
	int resetPassword(Account account,String checkCode);
}
