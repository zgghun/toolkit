package zgg.toolkit.system.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.system.base.BaseController;
import zgg.toolkit.system.constant.PerConst;
import zgg.toolkit.system.model.common.PageList;
import zgg.toolkit.system.model.common.PageParam;
import zgg.toolkit.system.model.dto.*;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserDetail;
import zgg.toolkit.system.service.UserService;

import javax.validation.Valid;

/**
 * Created by zgg on 2018/10/18
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @GetMapping("/find")
    public Object findUser(UserQuery query, PageParam pageParam) {
        PageList<User> users = userService.findUser(query, pageParam);
        return commonResult(users);
    }

    @RequiresPermissions(PerConst.user_save)
    @PostMapping("/save")
    public Object saveUser(@Valid UserSaveDto dto) {
        User user = userService.saveUser(dto);
        return commonResult(user);
    }

    @PostMapping("/enable")
    public Object enableUser(@Valid EnableDto dto) {
        userService.enableUser(dto);
        return commonResult();
    }

    @PostMapping("/setRole")
    public Object setUserRole(@Valid UserRoleSetDto dto) {
        userService.setUserRole(dto);
        return commonResult();
    }

    @GetMapping("/detail/get")
    public Object getUserExtend(@RequestParam Long id) {
        UserDetail detail = userService.getUserDetail(id);
        return commonResult(detail);
    }

    @PostMapping("/detail/save")
    public Object saveUserDetail(@Valid UserDetailSaveDto dto) {
        UserDetail extend = userService.saveUserDetail(dto, getLoginInfo());
        return commonResult(extend);
    }

    // 获取登录用户权限信息
    @GetMapping("/loginInfo")
    public Object getLoginUserInfo() {
        return commonResult(getLoginInfo());
    }
}
