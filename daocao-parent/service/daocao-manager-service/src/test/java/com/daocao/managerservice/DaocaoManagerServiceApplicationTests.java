package com.daocao.managerservice;

import com.daocao.dao.CategoryMapper;
import com.daocao.entity.Category;
import com.daocao.managerinterface.CategoryService;
import com.daocao.myentity.PageResult;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import javax.xml.ws.soap.Addressing;
import java.util.List;

@SpringBootTest(classes = DaocaoManagerServiceApplication.class)
class DaocaoManagerServiceApplicationTests {

    @Test
    void contextLoads() {
    }

    @Resource
    CategoryMapper categoryMapper;
    
    @Resource
    CategoryService categoryService;

    @Test
    public void categoryTest(){
        // 不使用service
        // List<Category> categories = categoryMapper.selectByExample(null);
        // System.out.println(categories);
        
        // 使用service
        List<Category> categoryList = categoryService.findAll();
        System.out.println(categoryList);
        System.out.println("-----------------------------------------------------");
        PageResult pageResult = categoryService.findPage(1, 2);
        List<Category> list = (List<Category>)pageResult.getRows();
        for (Category category : list) {
            System.out.println(category);
        }
        System.out.println(list);
    }

    @Test
    public void categoryPageSelectTest(){
        PageHelper.startPage(0, 2);
        Page<Category> page=   (Page<Category>) categoryMapper.selectByExample(null);
        List<Category> categories = page.getResult();
        for (Category category : categories) {
            System.out.println(category);
        }
    }

}
