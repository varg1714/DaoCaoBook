package com.daocao.orderinterface;

import com.daocao.entity.OrderItem;
import com.daocao.myentity.PageResult;

import java.util.List;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderItemService {

	/**
	 * 返回全部列表
	 * @return
	 */
	 List<OrderItem> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	 PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	 void add(OrderItem orderitem);
	
	
	/**
	 * 修改
	 */
	 void update(OrderItem orderitem);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	 OrderItem findOne(Integer id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	 void delete(Integer[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	 PageResult findPage(OrderItem orderitem, int pageNum, int pageSize);
	
}
