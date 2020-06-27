package com.daocao.pageweb.service;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Book;
import com.daocao.managerinterface.CategoryService;
import com.daocao.user.service.BookService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author varg
 * @date 2020/5/6 18:50
 */
@Service
public class BookHtmlService {

    @Autowired
    private Configuration configuration;

    @Reference
    private BookService bookService;

    @Reference
    private CategoryService categoryService;

    @Value(("${directPath}"))
    String directPath;

    public boolean geneHtml(Integer[] ids) throws IOException, TemplateException {

        Template template = configuration.getTemplate("book_details.ftl");
        Writer writer = null;
        // 获取书籍数据
        for (Integer id : ids) {
            Book book = bookService.findOne(id);

            // 获取书籍分类
            Map<String,String> category = new HashMap<>(4);
            if(book.getBooktype1() != null){
                category.put("cate1",categoryService.findOne(book.getBooktype1()).getName());
            }
            if(book.getBooktype2() != null){
                category.put("cate2",categoryService.findOne(book.getBooktype2()).getName());
            }

            // 封装书籍信息
            Map<String,Object> bookMap = new HashMap<>(4);
            bookMap.put("book",book);
            bookMap.put("category",category);

            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(directPath+book.getId()+".html"), StandardCharsets.UTF_8)));
            template.process(bookMap,writer);

        }

        return true;
    }

    public boolean deleHtme(Integer[] ids){
        File file = null;
        for (Integer id : ids) {
            file = new File(directPath+id+".html");
            if(file.exists()){
                boolean delete = file.delete();
                if(!delete){
                    return false;
                }
            }
        }

        return true;
    }
}
