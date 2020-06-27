package com.daocao.userweb.controller;

import java.util.List;

import com.daocao.entity.Book;
import com.daocao.entity.Category;
import com.daocao.managerinterface.CategoryService;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.ResponseEntity;
import com.daocao.myentity.Result;
import com.daocao.user.service.BookService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

import javax.validation.Valid;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/book")
public class BookController {

    @Reference
    private BookService bookService;

    @Reference
    private CategoryService categoryService;

    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findAll")
    public List<Book> findAll() {
        return bookService.findAll();
    }


    /**
     * 返回全部列表
     *
     * @return
     */
    @RequestMapping("/findPage")
    public PageResult findPage(int page, int rows) {
        return bookService.findPage(page, rows);
    }

    /**
     * 书籍发布
     *
     * @param book 用户传入的书籍信息
     * @return
     */
    @PostMapping("/add")
    public ResponseEntity<String> add(@Valid @RequestBody Book book) {
        // 设置商品的出售人
        String seller = SecurityContextHolder.getContext().getAuthentication().getName();
        book.setSeller(seller);
        bookService.add(book);
        return new ResponseEntity<>(true, "书籍出售成功");
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @RequestMapping("/findOne")
    public Book findOne(Integer id) {
        return bookService.findOne(id, SecurityContextHolder.getContext().getAuthentication().getName());
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @RequestMapping("/delete")
    public Result delete(Integer[] ids) {
        try {
            bookService.delete(ids);
            return new Result(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "删除失败");
        }
    }

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@Valid @RequestBody Book book, int page, int rows) {
        return bookService.findPage(book, page, rows);
    }

    @GetMapping("/getCategory")
    public List<Category> getCategory(Integer parentId) {
        if (parentId >= 0) {
            return categoryService.findByParentId(parentId);
        }
        return null;
    }

    @GetMapping("/findSellingBook")
    public ResponseEntity<PageResult> findSellingBook(boolean isSellOut, int pageNum) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        PageResult result = bookService.findSellingBook(username, isSellOut, pageNum, 5);
        return new ResponseEntity<>(true, "", result);
    }

    @GetMapping("/getSellingBooks")
    public ResponseEntity<PageResult> getSellingBooks(boolean isSellOut, int pageNum) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        PageResult books = bookService.findSellingBook(username, isSellOut, pageNum, 5);
        return new ResponseEntity<>(true, "", books);
    }

    @PostMapping("/refillBook")
    public ResponseEntity<String> refillBook(int id, int number) {
        bookService.refillBook(id, number, SecurityContextHolder.getContext().getAuthentication().getName());
        return new ResponseEntity<>(true, "库存补充成功");
    }

    @PostMapping("/updateBaseInfo")
    public ResponseEntity<String> updateBaseInfo(@Valid @RequestBody Book book) {
        book.setSeller(SecurityContextHolder.getContext().getAuthentication().getName());
        bookService.updateBaseInfo(book);
        return new ResponseEntity<>(true, "书籍信息已更新");
    }

}
