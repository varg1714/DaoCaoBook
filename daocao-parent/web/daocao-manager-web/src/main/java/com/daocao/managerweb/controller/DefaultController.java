package com.daocao.managerweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author varg
 * @date 2020/4/10 1:36
 */
@Controller
public class DefaultController {

    @RequestMapping("/")
    public String defaultPage(){
        return "redirect:admin/index.html";
    }

}
