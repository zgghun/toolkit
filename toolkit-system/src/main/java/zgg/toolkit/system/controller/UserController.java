package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.model.PageList;
import zgg.toolkit.core.model.PageParam;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.DeleteBatchDto;
import zgg.toolkit.system.model.dto.UserExtendSaveDto;
import zgg.toolkit.system.model.dto.UserQuery;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.model.entity.UserDetail;
import zgg.toolkit.system.service.UserService;

import javax.validation.Valid;

/**
 * Created by zgg on 2018/10/18
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends SystemBaseController {
    @Autowired
    private UserService userService;

    @PostMapping("/extend/save")
    public Object saveUserExtend(@Valid UserExtendSaveDto dto){
        UserDetail extend =  userService.saveUserExtend(dto, getLoginInfo());
        return commonResult(extend);
    }

    @PostMapping("/delete")
    public Object deleteUser(@Valid DeleteBatchDto dto){
        userService.deleteUser(dto.getIds());
        return commonResult();
    }

    @GetMapping("/list")
    public Object listUser(UserQuery query, PageParam pageParam){
        PageList<User> users = userService.findUser(query, pageParam);
        System.out.println(users.toString());
        return commonResult(users);
    }

    @PostMapping("/save")
    public Object saveUser(@Valid UserSaveDto dto){
        User user = userService.saveUser(dto);
        return commonResult(user);
    }
}
