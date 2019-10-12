package com.restful.api.service;

import com.restful.api.model.Account;
import com.restful.api.repository.AccountRepository;
import com.restful.api.response.AccountResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//Khi người dùng đăng nhập thì Spring Security sẽ cần lấy các thông tin UserDetails hiện có để kiểm tra.
//Vì vậy, bạn cần tạo ra một class kế thừa lớp UserDetailsService mà Spring Security cung cấp để làm nhiệm vụ này.
@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AccountResponse user = accountRepository.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRole()));

        return new org.springframework.security.core.userdetails.User(user.getName(),
                user.getPassword(),
                authorities);
    }
}
