package com.test.dbhappy.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_user")
public class LoginUser {
    private String id;
    private String username;
    private String password;
    private String role;
}
