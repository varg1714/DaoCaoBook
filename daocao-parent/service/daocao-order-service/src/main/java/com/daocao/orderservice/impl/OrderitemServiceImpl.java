package com.daocao.orderservice.impl;
import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.OrderItemMapper;
import com.daocao.entity.OrderItem;
import com.daocao.entity.OrderItemExample;
import com.daocao.myentity.PageResult;
import com.daocao.orderinterface.OrderItemService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;


/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class OrderitemServiceImpl implements OrderItemService {

	@Resource
	private OrderItemMapper orderitemMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<OrderItem> findAll() {
		return orderitemMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<OrderItem> page=   (Page<OrderItem>) orderitemMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(OrderItem orderitem) {
		orderitemMapper.insert(orderitem);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(OrderItem orderitem){
		orderitemMapper.updateByPrimaryKey(orderitem);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public OrderItem findOne(Integer id){
		return orderitemMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Integer[] ids) {
		for(Integer id:ids){
			orderitemMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(OrderItem orderitem, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		OrderItemExample example=new OrderItemExample();
			OrderItemExample.Criteria criteria = example.createCriteria();
		
		if(orderitem!=null){			
						if(orderitem.getGoodsname()!=null && orderitem.getGoodsname().length()>0){
				criteria.andGoodsnameLike("%"+orderitem.getGoodsname()+"%");
			}
			if(orderitem.getGoodsimage()!=null && orderitem.getGoodsimage().length()>0){
				criteria.andGoodsimageLike("%"+orderitem.getGoodsimage()+"%");
			}
	
		}
		
		Page<OrderItem> page= (Page<OrderItem>)orderitemMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
