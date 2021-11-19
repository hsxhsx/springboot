package com.test.dbhappy.kevin.service;

import java.util.List;

import com.test.dbhappy.kevin.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-19
 */
public interface UserService extends IService<User> {
        int batchInsert(List<User> list);

        int batchUpdate(List<User> list);
}
