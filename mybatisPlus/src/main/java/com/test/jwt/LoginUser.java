package com.test.jwt;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

@Data
@ApiModel("登录返回实体")
public class LoginUser {

   @ApiModelProperty("登录令牌")
   public String token;

   @ApiModelProperty("登录令牌过期时间")
   @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
   public Date expirationDate;

   @ApiModelProperty("登录返回对象信息")
   public UserDetails userDetails;

}
