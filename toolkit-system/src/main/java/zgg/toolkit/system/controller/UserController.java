package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.controller.BaseController;
import zgg.toolkit.system.model.dto.UserSaveDto;
import zgg.toolkit.system.model.entity.User;
import zgg.toolkit.system.service.UserService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zgg on 2018/10/18
 */
@RestController
@RequestMapping("/sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public Object listUser(){
        List<User> users = userService.listUser();
        return commonResult(users);
    }

    @RequestMapping("/saveUser")
    public Object saveUser(@Valid UserSaveDto dto){
        User user = userService.saveUser(dto);
        return commonResult(user);
    }
}
