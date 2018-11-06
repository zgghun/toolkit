package zgg.toolkit.system.model.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

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

    // 用户可访问模块，模块结构交由前端控制，后端仅告知启用哪些模块
    private List<Map<String, String>> modules;
    // 用户拥有的权限
    private List<String> permissions;
}
