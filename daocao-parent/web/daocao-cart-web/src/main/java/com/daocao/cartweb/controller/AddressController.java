package com.daocao.cartweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Address;
import com.daocao.myentity.Result;
import com.daocao.user.service.AddressService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author varg
 * @date 2020/4/18 22:40
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    AddressService addressService;

    @PostMapping("/addAddress")
    public Result addAddress(@Valid @RequestBody Address address) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setOwner(name);
        address.setIsdefault("0");
        addressService.add(address);
        return new Result(true, "地址增加成功");
    }

    @GetMapping("/getAddress")
    public List<Address> getAddress() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return addressService.findByUser(name);
    }


}
