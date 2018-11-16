package zgg.toolkit.system.mapper;

import org.apache.ibatis.annotations.Param;
import zgg.toolkit.core.model.MapVO;
import zgg.toolkit.system.model.dto.UserQuery;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.entity.User;

import java.util.List;

/**
 * Created by zgg on 2018/11/07
 */

public interface UserExtendMapper {
    List<User> findUser(UserQuery query);

    List<Permission> findLoginUserPer(Long userId);

    List<MapVO> findUserModule(@Param("moduleIds") List<Long> moduleIds);
}
