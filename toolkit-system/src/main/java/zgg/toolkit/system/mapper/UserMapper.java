package zgg.toolkit.system.mapper;

import zgg.toolkit.core.mapper.BaseMapper;
import zgg.toolkit.system.model.entity.User;

public interface UserMapper extends BaseMapper<User> {

    void  addUser(User user);
}