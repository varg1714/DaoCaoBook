package com.daocao.userweb.controller;

import java.util.List;

import com.daocao.entity.Address;
import com.daocao.myentity.PageResult;
import com.daocao.myentity.ResponseEntity;
import com.daocao.myentity.Result;
import com.daocao.user.service.AddressService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.alibaba.dubbo.config.annotation.Reference;

import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/address")
public class AddressController {

    @Reference
    private AddressService addressService;

    /**
     * 查询+分页
     *
     * @param
     * @param page
     * @param rows
     * @return
     */
    @RequestMapping("/search")
    public PageResult search(@Valid @RequestBody Address address, int page, int rows) {
        return addressService.findPage(address, page, rows);
    }

    @PostMapping("/addAddress")
    public ResponseEntity<String> addAddress(@Valid @RequestBody Address address) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        address.setOwner(name);
        address.setIsdefault("0");
        addressService.add(address);
        return new ResponseEntity<>(true, "地址增加成功");
    }

    @GetMapping("/getAddress")
    public ResponseEntity<List<Address>> getAddress() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        return new ResponseEntity<>(true, "", addressService.findByUser(name));
    }

    @PostMapping("/setDefaultAddress")
    public ResponseEntity<String> setDefaultAddress(int id) {

        if (id <= 0) {
            throw new IllegalArgumentException("地址ID非法");
        }
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        addressService.setDefaultAddress(id, name);
        return new ResponseEntity<>(true, "默认地址设置成功");
    }

}
