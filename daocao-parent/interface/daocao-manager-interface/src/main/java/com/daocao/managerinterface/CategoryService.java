package com.daocao.managerinterface;
import java.util.List;

import com.daocao.entity.Category;
import com.daocao.myentity.PageResult;

/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface CategoryService {

	/**
	 * 返回全部列表
	 * @return
	 */
	 List<Category> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	 PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	 void add(Category category);
	
	
	/**
	 * 修改
	 */
	 void update(Category category);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	 Category findOne(Integer id);
	
	
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
	 PageResult findPage(Category category, int pageNum, int pageSize);

	List<Category> findByParentId(Integer parentId);
	
}
