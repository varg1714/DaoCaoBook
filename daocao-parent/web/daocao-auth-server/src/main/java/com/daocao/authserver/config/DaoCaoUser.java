package com.daocao.authserver.config;

import com.daocao.entity.Account;
import com.daocao.user.service.AccountService;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author varg
 * @date 2020/4/11 21:13
 */
public class DaoCaoUser implements UserDetailsService {


    AccountService accountService;

    public DaoCaoUser(AccountService accountService){
        this.accountService = accountService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
         System.out.println("username == null"+(username == null)+"username:"+username);
         System.out.println("accountService == null"+(accountService == null)+"accountService"+accountService);
        Account account = accountService.findOne(username);
        if(account != null){
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));

            return new User(account.getUsername(),account.getPassword(),authorities);
        }
        return null;
    }
}
