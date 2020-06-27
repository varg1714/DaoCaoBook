package com.daocao.searchweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Book;
import com.daocao.myentity.PageResult;
import com.daocao.search.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author varg
 * @date 2020/4/16 1:20
 */

@Controller
public class SearchController {

    @Reference
    SearchService searchService;

    @PostMapping("/search")
    @ResponseBody
    public PageResult search(@RequestBody Map<String,String> map) {
        return searchService.search(map);
    }

    @RequestMapping("/")
    public String defaultController(){
        return "forward:/booksearch.html";
    }


}
