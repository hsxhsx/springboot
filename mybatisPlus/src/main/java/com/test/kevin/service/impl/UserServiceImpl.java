package com.test.kevin.service.impl;

import com.test.kevin.entity.User;
import com.test.kevin.mapper.UserMapper;
import com.test.kevin.service.UserService;
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
