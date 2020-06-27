package com.daocao.managerweb.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Category;
import com.daocao.managerinterface.CategoryService;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.Result;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Reference
    private CategoryService categoryService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<Category> findAll() {
        return categoryService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return categoryService.findPage(page, rows);
    }

    /**
     * 增加
     *
     * @param category
     * @return
     */
    @PostMapping("/add")
    public Result add(@Valid @RequestBody Category category) {
        try {
            categoryService.add(category);
            return new com.daocao.myentity.Result(true, "增加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "增加失败");
        }
    }

    /**
     * 修改
     *
     * @param category
     * @return
     */
    @PostMapping("/update")
    public Result update(@Valid @RequestBody Category category) {
        try {
            categoryService.update(category);
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Category findOne(Integer id) {
        return categoryService.findOne(id);
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(Integer[] ids) {
        try {
            categoryService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param category
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@RequestBody Category category, int page, int rows) {
        return categoryService.findPage(category, page, rows);
    }

    @GetMapping("/findByParentId")
    public List<Category> findByParentId(Integer parentId) {
        return categoryService.findByParentId(parentId);
    }

}
