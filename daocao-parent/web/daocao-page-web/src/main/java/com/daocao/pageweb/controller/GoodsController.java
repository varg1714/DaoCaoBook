package com.daocao.pageweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Book;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.ResponseEntity;
import com.daocao.user.service.BookService;
import org.springframework.web.bind.annotation.*;

/**
 * @author varg
 * @date 2020/4/14 18:23
 */
@RestController
public class GoodsController {

    @Reference
    BookService bookService;

    @GetMapping("/getGoods/{id}")
    public Book getGoods(@PathVariable(value = "id") Integer id) {
        return bookService.findOne(id);
    }

    @GetMapping("/getBookEval")
    public ResponseEntity<PageResult> getBookEval(@RequestParam int id, int pageNum) {
        if (id <= 0) {
            throw new IllegalArgumentException("书籍索引非法");
        }

        PageResult bookEvaluation = bookService.getBookEvaluation(id, pageNum, 15);
        return new ResponseEntity<>(true, "", bookEvaluation);
    }

    @GetMapping("/getRecommendBooks")
    public ResponseEntity<PageResult> getRecommendBooks(){
        PageResult books = bookService.getRecommendBooks(1, 15);
        return new ResponseEntity<>(true,"",books);
    }
}
