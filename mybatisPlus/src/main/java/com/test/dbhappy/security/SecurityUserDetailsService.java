package com.test.dbhappy.security;

import com.baomidou.dynamic.datasource.toolkit.DynamicDataSourceContextHolder;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.test.dbhappy.entity.LoginUser;
import com.test.dbhappy.service.LoginUserService;
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
    private LoginUserService loginUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        DynamicDataSourceContextHolder.push("master");
        QueryWrapper<LoginUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        LoginUser loginUser = loginUserService.getOne(queryWrapper);
        System.out.println("JwtUserDetailsService:" + loginUser);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        if (ObjectUtils.isEmpty(loginUser)) return null;
        //此处做业务逻辑处理，角色权限查询
        if (!StringUtils.isEmpty(loginUser.getRole())) {
            List<String> roleList = Arrays.asList(loginUser.getRole().split(","));
            roleList.stream().forEach(role -> {
                        authorityList.add(new SimpleGrantedAuthority(role));
                    }
            );
        }
        //构建security 角色权限对象
        return new SecurityLoginUserDetails(loginUser, authorityList);
    }

}
