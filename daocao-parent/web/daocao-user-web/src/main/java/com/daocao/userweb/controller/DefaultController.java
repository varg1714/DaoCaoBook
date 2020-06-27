package com.daocao.userweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author varg
 * @date 2020/4/10 1:36
 */
@Controller
public class DefaultController {

    @RequestMapping("/")
    public String defaultPage(){
        return "redirect:/user/home-index.html";
    }

}
