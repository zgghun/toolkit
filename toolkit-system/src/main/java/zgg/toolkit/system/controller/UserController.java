package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.core.model.PageList;
import zgg.toolkit.core.model.PageParam;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.DeleteBatchDto;
import zgg.toolkit.system.model.dto.UserDetailSaveDto;
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

    @GetMapping("/detail/get")
    public Object getUserExtend(@RequestParam Long id){
        UserDetail detail = userService.getUserDetail(id);
        return commonResult(detail);
    }

    @PostMapping("/detail/save")
    public Object saveUserDetail(@Valid UserDetailSaveDto dto){
        UserDetail extend =  userService.saveUserDetail(dto, getLoginInfo());
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
        return commonResult(users);
    }

    @PostMapping("/save")
    public Object saveUser(@Valid UserSaveDto dto){
        User user = userService.saveUser(dto);
        return commonResult(user);
    }
}
