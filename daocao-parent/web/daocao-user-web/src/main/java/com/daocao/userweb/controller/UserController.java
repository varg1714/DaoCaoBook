package com.daocao.userweb.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.daocao.entity.Account;
import com.daocao.myentity.ResponseEntity;
import com.daocao.myentity.Result;
import com.daocao.user.service.AccountService;
import com.daocao.userweb.utils.PhoneFormatCheckUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Date;
import java.util.Map;

/**
 * controller
 *
 * @author Administrator
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Reference
    private AccountService accountService;

    /**
     * 注册
     *
     * @param account
     * @return
     */
    @PostMapping("/regist")
    public ResponseEntity<String> add(@Valid @RequestBody Account account, String smsCode) {

        boolean checkTelCode = accountService.checkTelCode(account.getTel(), smsCode);
        if(checkTelCode){
            try {
                // 对密码进行加密
                String encode = new BCryptPasswordEncoder().encode(account.getPassword());
                account.setPassword(encode);

                accountService.add(account);
                return new ResponseEntity<>(true,"注册成功");
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>(false,"注册失败，请稍后再试");
            }
        }else {
            return new ResponseEntity<>(false,"注册失败，短信验证码校验错误");
        }


    }

    /**
     * 修改
     *
     * @param account
     * @return
     */
    @PostMapping("/updateBaseInfo")
    public Result updateBaseInfo(@Valid @RequestBody Account account) {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            account.setUsername(username);
            int result = accountService.updateBaseInfo(account);
            if (result != 1) {
                throw new Exception("更新失败");
            }
            return new Result(true, "修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "修改失败");
        }
    }

    /**
     * 获取实体
     *
     * @param id
     * @return
     */
    @GetMapping("/findOne")
    public Account findOne(String id) {
        return accountService.findOne(id);
    }

    @GetMapping("/getUserName")
    @CrossOrigin(origins = "http://localhost:8083,http://localhost:8084", allowCredentials = "true")
    public String getUserName() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return accountService.findOne(username).getNickname();
    }

    /**
     * 获取注册时的短信验证码
     * @param tel 手机号
     * @return
     */
    @GetMapping("/getSmsCode")
    public ResponseEntity<String> getSmsCode(String tel){
        if(tel == null || !PhoneFormatCheckUtils.isPhoneLegal(tel)){
            return new ResponseEntity<>(false,"短信发送失败，请检车手机号后重试");
        }
        boolean sendTelCode = accountService.sendTelCode(tel);
        if(sendTelCode){
            return new ResponseEntity<>(true,"短信发送成功");
        }else {
            return new ResponseEntity<>(false,"短信发送失败，请稍后再试");
        }
    }

    /**
     * 获取修改密码时的短信验证码
     * @return
     */
    @GetMapping("/getResetPwdCode")
    public ResponseEntity<String> getResetPwdCode(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        // 获取登陆用户的手机号
        Account account = accountService.findOne(name);
        if(account != null){
            // 往该手机号发送验证码
            accountService.sendTelCode(account.getTel());
            return new ResponseEntity<>(true,"验证码已发送到您的绑定手机");
        }
        return new ResponseEntity<>(false,"验证码发送失败");
    }

    @PostMapping("/resetPassword")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String,String> map){
        String password = map.get("password");
        if(password == null || password.length() == 0){
            throw new IllegalArgumentException("密码不饿能为空");
        }
        String checkCode = map.get("checkCode");
        if(checkCode == null || checkCode.length() == 0){
            throw new IllegalArgumentException("验证码不能为空");
        }

        Account account = new Account();
        // 密码加密 更新日期设置 手机设置
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        account.setPassword(new BCryptPasswordEncoder().encode(password));
        account.setUsername(name);
        account.setUpdatedate(new Date());
        account.setTel(accountService.findOne(name).getTel());
        // 密码更新
        int result = accountService.resetPassword(account, checkCode);

        if(result == -1){
            return new ResponseEntity<>(false,"验证码错误");
        }else if(result == 0){
            return new ResponseEntity<>(false,"密码修改失败");
        }
        return new ResponseEntity<>(true,"密码修改成功");
    }
}
