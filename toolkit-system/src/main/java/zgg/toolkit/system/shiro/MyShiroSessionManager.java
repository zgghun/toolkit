package zgg.toolkit.system.shiro;

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

/**
 * Created by zgg on 2018/10/31
 * 自定义shiro的session管理
 */

public class MyShiroSessionManager extends DefaultWebSessionManager {
    public MyShiroSessionManager() {
        super();
    }
}
