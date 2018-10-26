package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.core.controller.BaseController;
import zgg.toolkit.system.model.dto.RoleSaveDto;
import zgg.toolkit.system.model.entity.Role;
import zgg.toolkit.system.service.RoleService;

/**
 * Created by zgg on 2018/10/18
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @RequestMapping("/saveRole")
    public Object saveRole(RoleSaveDto dto){
        Role role = roleService.saveRole(dto);
        return commonResult(role);
    }
}
