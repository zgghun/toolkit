package zgg.toolkit.system.model.vo;

import lombok.Data;
import zgg.toolkit.system.model.common.MapVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zgg on 2018/10/26
 * 用户登录后返回给前台的一些信息
 * 包括 基础信息、权限信息...
 */
@Data
public class LoginInfo implements Serializable {
    private Long userId;
    private String username;
    private String tel;
    private String avatar;

    // 可访问模块
    private List<MapVo> modules;
    // 用户拥有的权限
    private List<String> permissions;
}
