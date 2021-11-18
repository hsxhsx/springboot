package com.test.dbhappy.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.dbhappy.kevin.entity.User;
import com.test.dbhappy.kevin.service.UserService;
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
public class SecurityUserDetailsService implements UserDetailsService {

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
        //此处做业务逻辑处理，角色权限查询
        if (!StringUtils.isEmpty(user.getRole())) {
            List<String> roleList = Arrays.asList(user.getRole().split(","));
            roleList.stream().forEach(role -> {
                        authorityList.add(new SimpleGrantedAuthority(role));
                    }
            );
        }
        //构建security 角色权限对象
        return new SecurityUserDetails(user, authorityList);
    }

}