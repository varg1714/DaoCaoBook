package com.daocao.user.service;

import com.daocao.entity.Address;
import com.daocao.myentity.PageResult;

import java.util.List;

/**
 * 服务层接口
 *
 * @author Administrator
 */
public interface AddressService {

    /**
     * 返回全部列表
     *
     * @return
     */
    List<Address> findAll();


    /**
     * 返回分页列表
     *
     * @return
     */
    PageResult findPage(int pageNum, int pageSize);


    /**
     * 增加
     */
    void add(Address address);


    /**
     * 修改
     */
    void update(Address address);


    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    Address findOne(Integer id);


    /**
     * 批量删除
     *
     * @param ids
     */
    void delete(Integer[] ids,String username);

    /**
     * 分页
     *
     * @param pageNum  当前页 码
     * @param pageSize 每页记录数
     * @return
     */
    PageResult findPage(Address address, int pageNum, int pageSize);

	/**
	 * 获取该用户的全部地址
	 * @param username 所属用户
	 * @return
	 */
	List<Address> findByUser(String username);

    /**
     * 设置默认收货地址
     * @param id 地址ID
     * @param username 用户名
     * @return
     */
	int setDefaultAddress(int id,String username);

}
