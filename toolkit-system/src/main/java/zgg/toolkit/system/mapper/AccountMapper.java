package zgg.toolkit.system.mapper;

import zgg.toolkit.system.model.vo.LoginInfo;

/**
 * Created by zgg on 2018/10/31
 */

public interface AccountMapper {
    LoginInfo getLoginUserInfo(Long userId);
}
