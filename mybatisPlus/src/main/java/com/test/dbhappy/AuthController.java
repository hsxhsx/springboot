package com.test.dbhappy;

import com.test.dbhappy.entity.LoginUser;
import com.test.dbhappy.gen.R;
import com.test.dbhappy.jwt.JwtTokenUtil;
import com.test.dbhappy.service.AsyncTask;
import com.test.dbhappy.service.LoginUserService;
import com.test.dbhappy.utils.EasyExcelUtil;
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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
@RequestMapping("auth")
public class AuthController {

    @Autowired
    @Qualifier("securityUserDetailsService")
    private UserDetailsService userDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private LoginUserService loginUserService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AsyncTask asyncTask;

    @PostMapping("login")
    public R<com.test.dbhappy.jwt.LoginUser> login(@RequestBody LoginUser user){
        final UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUsername());
        if(userDetails!=null && passwordEncoder.matches(user.getPassword(),userDetails.getPassword())) {
            Map hashMap = jwtTokenUtil.generateToken(user.getUsername());
            com.test.dbhappy.jwt.LoginUser loginUser = new com.test.dbhappy.jwt.LoginUser();
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
    @PostMapping("addUser")
    public R add(@RequestBody LoginUser admin  ) {
        //获取用户的密码，并调用encode函数进行加密，加密后的密码在放入实体类中
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //调用service层的的添加方法添加用户
        try {
            loginUserService.save(admin);
        } catch (Exception exception) {
           return R.fail("已存在相同用户");
        }
        //返回结果
        return R.ok("新增成功");
    }

    @PostMapping("refreshToke")
    public R<com.test.dbhappy.jwt.LoginUser> refreshToke(@RequestBody @Valid LoginUser user  ) {
        Map hashMap = jwtTokenUtil.generateToken(user.getUsername());
        com.test.dbhappy.jwt.LoginUser loginUser = new com.test.dbhappy.jwt.LoginUser();
        loginUser.setExpirationDate((Date) hashMap.get("expirationDate"));
        loginUser.setToken((String) hashMap.get("token"));
        return R.ok(loginUser);
    }

    @GetMapping("test")
    public void getTask() throws InterruptedException, ExecutionException {
        for(int i = 0; i<100;i++) {
            asyncTask.tesTask(i);
            Future<String> futureTask = asyncTask.stringTask(i+"测试");
            String string = futureTask.get();//阻碍线程顺序输出（同步线程）
            System.out.println(string);
        }
    }

    @GetMapping("export")
    public void export(HttpServletResponse response) throws IOException {

        //模拟从数据库获取需要导出的数据
        List<LoginUser> personList = new ArrayList<>();
        for(int i = 0; i<10;i++) {
            LoginUser person1 = new LoginUser().setUsername("测试"+i).setCreateTime(new Date()).setPhoneNumber("1500712650"+i);

            personList.add(person1);
        }
        //导出操作
        EasyExcelUtil.exportExcel(personList,"花名册","草帽一伙",LoginUser.class,"海贼王.xls",response);
    }

    @PostMapping("importExcel")
    public void importExcel() throws Exception {
        String filePath = "F:\\海贼王.xls";
        //解析excel，
        List<LoginUser> personList = EasyExcelUtil.importExcel(filePath,1,1,LoginUser.class);
        //也可以使用MultipartFile,使用 FileUtil.importExcel(MultipartFile file, Integer titleRows, Integer headerRows, Class<T> pojoClass)导入
        System.out.println("导入数据一共【"+personList.size()+"】行");
        //TODO 保存数据库
    }
}
