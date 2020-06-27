package com.daocao.managerservice.impl;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Service;
import com.daocao.dao.CategoryMapper;
import com.daocao.entity.Category;
import com.daocao.entity.CategoryExample;
import com.daocao.managerinterface.CategoryService;
import com.daocao.myentity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import javax.annotation.Resource;


/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 查询全部
     */
    @Override
    public List<Category> findAll() {
        return categoryMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<Category> page = (Page<Category>) categoryMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(Category category) {
        categoryMapper.insert(category);
    }


    /**
     * 修改
     */
    @Override
    public void update(Category category) {
        categoryMapper.updateByPrimaryKey(category);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public Category findOne(Integer id) {
        return categoryMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Integer[] ids) {
        for (Integer id : ids) {
            categoryMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(Category category, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();

        if (category != null) {
            if (category.getParentid() != null) {
                criteria.andParentidEqualTo(category.getParentid());
            }

        }

        Page<Category> page = (Page<Category>) categoryMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public List<Category> findByParentId(Integer parentId) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentidEqualTo(parentId);
        return categoryMapper.selectByExample(example);
    }

}
