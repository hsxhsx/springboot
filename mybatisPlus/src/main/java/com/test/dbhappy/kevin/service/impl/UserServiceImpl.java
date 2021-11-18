package com.test.dbhappy.kevin.service.impl;

import com.test.dbhappy.kevin.entity.User;
import com.test.dbhappy.kevin.mapper.UserMapper;
import com.test.dbhappy.kevin.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
