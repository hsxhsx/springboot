package com.test;

import com.test.gen.R;
import com.test.jwt.JwtTokenUtil;
import com.test.kevin.entity.User;
import com.test.kevin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    @Qualifier("securityUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public R login(@RequestBody User user){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(userDetails!=null && passwordEncoder.matches(user.getPassword(),userDetails.getPassword())) {
            final String token = jwtTokenUtil.generateToken(user.getUsername());
            //添加 start
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return R.ok(token);
        }
        return R.fail("账号或者密码错误");
    }


    /**
     * 增加
     * @param admin 用户实体类
     */
    @Secured("ROLE_ADMIN")
    @PostMapping("/adduser")
    public R add(@RequestBody User admin  ) {
        //获取用户的密码，并调用encode函数进行加密，加密后的密码在放入实体类中
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //调用service层的的添加方法添加用户
        userService.save(admin);
        //返回结果
        return R.ok("新增成功");
    }
}
