package zgg.toolkit.system.mapper;

import zgg.toolkit.system.model.entity.Permission;

import java.util.List;

/**
 * Created by zgg on 2018/10/31
 */

public interface AccountMapper {
    List<Permission> findLoginUserPer(Long userId);
}
