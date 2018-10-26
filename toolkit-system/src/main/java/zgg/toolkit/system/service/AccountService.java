package zgg.toolkit.system.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zgg.toolkit.core.enums.ResultCode;
import zgg.toolkit.core.exception.BaseException;
import zgg.toolkit.core.utils.HelpUtils;
import zgg.toolkit.system.mapper.UserMapper;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserExample;

import java.util.List;

/**
 * Created by zgg on 2018/10/25
 */
@Service
public class AccountService {
    @Autowired
    private UserMapper userMapper;

    /**
     * shiro 查询用户
     *
     * @param username
     * @param password
     * @return
     */
    public User getUser(String username, String password) {
        UserExample example = new UserExample();
        UserExample.Criteria criteria = example.createCriteria();
        if (HelpUtils.isNumeric(username)) {
            // 全数字，电话登陆
            criteria.andTelEqualTo(username);
        } else if (HelpUtils.contains(username, "@")) {
            // 包含 @ 邮箱登陆
            criteria.andEmailEqualTo(username);
        } else {
            // 用户名登陆
            criteria.andUsernameEqualTo(username);
        }
        criteria.andPasswordEqualTo(password);
        List<User> users = userMapper.selectByExample(example);
        if (users.size() == 1){
            return users.get(0);
        }
        if (users.size() > 1){
            throw new BaseException(ResultCode.MORE_THAN_ONE_ERROT);
        }
        return null;
    }
}
