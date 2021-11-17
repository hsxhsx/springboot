package com.test.jwt;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.kevin.entity.User;
import com.test.kevin.service.UserService;
import com.test.security.SecurityUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userService.getOne(queryWrapper);
        System.out.println("JwtUserDetailsService:" + user);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (ObjectUtils.isEmpty(user)) return null;
        if (!StringUtils.isEmpty(user.getRole())) {
            List<String> roleList = Arrays.asList(user.getRole().split(","));
            roleList.stream().forEach(role -> {
                        authorityList.add(new SimpleGrantedAuthority(role));
                    }
            );
        }
        return new SecurityUserDetails(user, authorityList);
    }

}
