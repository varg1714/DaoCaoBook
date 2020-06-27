package com.daocao.authserver.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @author varg
 * @date 2020/5/4 17:11
 */
@RestController
public class UserController {

    @GetMapping("/user")
    public Principal user(Principal principal){
        return principal;
    }

}
