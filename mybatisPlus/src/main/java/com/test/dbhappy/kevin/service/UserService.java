package com.test.dbhappy.kevin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.test.dbhappy.kevin.entity.User;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 胡双喜
 * @since 2021-11-18
 */
public interface UserService extends IService<User> {
        int batchInsert(List<User> list);

        int batchUpdate(List<User> list);
}
