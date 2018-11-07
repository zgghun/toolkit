package zgg.toolkit.system.mapper;

import zgg.toolkit.system.model.dto.UserQuery;
import zgg.toolkit.system.model.entity.User;

import java.util.List;

/**
 * Created by zgg on 2018/11/07
 */

public interface UserExtendMapper {
    List<User> findUser(UserQuery query);
}
