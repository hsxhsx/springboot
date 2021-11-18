package com.test.dbhappy;

import com.test.dbhappy.gen.R;
import com.test.dbhappy.jwt.LoginUser;
import com.test.dbhappy.jwt.JwtTokenUtil;
import com.test.dbhappy.kevin.entity.User;
import com.test.dbhappy.kevin.service.UserService;
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

import javax.validation.Valid;
import java.util.Date;
import java.util.Map;

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
    public R<LoginUser> login(@RequestBody User user){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(userDetails!=null && passwordEncoder.matches(user.getPassword(),userDetails.getPassword())) {
            Map hashMap = jwtTokenUtil.generateToken(user.getUsername());
            LoginUser loginUser = new LoginUser();
            loginUser.setExpirationDate((Date) hashMap.get("expirationDate"));
            loginUser.setToken((String) hashMap.get("token"));
            loginUser.setUserDetails(userDetails);
            //添加 start
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return R.ok(loginUser);
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

    @PostMapping("/refreshToke")
    public R<LoginUser> refreshToke(@RequestBody @Valid User user  ) {
        Map hashMap = jwtTokenUtil.generateToken(user.getUsername());
        LoginUser loginUser = new LoginUser();
        loginUser.setExpirationDate((Date) hashMap.get("expirationDate"));
        loginUser.setToken((String) hashMap.get("token"));
        return R.ok(loginUser);
    }
}
