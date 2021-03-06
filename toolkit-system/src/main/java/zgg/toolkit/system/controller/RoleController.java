package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.system.base.BaseController;
import zgg.toolkit.system.model.common.DeleteDto;
import zgg.toolkit.system.model.common.PageList;
import zgg.toolkit.system.model.common.PageParam;
import zgg.toolkit.system.model.dto.EnableDto;
import zgg.toolkit.system.model.dto.RolePerSetDto;
import zgg.toolkit.system.model.dto.RoleQuery;
import zgg.toolkit.system.model.dto.RoleSaveDto;
import zgg.toolkit.system.model.entity.Role;
import zgg.toolkit.system.service.RoleService;

import javax.validation.Valid;

/**
 * Created by zgg on 2018/10/18
 */
@RestController
@RequestMapping("/sys/role")
public class RoleController extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/find")
    public Object findRole(RoleQuery query, PageParam pageParam) {
        PageList<Role> list = roleService.listRole(query, pageParam);
        return commonResult(list);
    }

    @PostMapping("/save")
    public Object saveRole(@Valid RoleSaveDto dto) {
        Role role = roleService.saveOrUpdateRole(dto);
        return commonResult(role);
    }

    @PostMapping("/enable")
    public Object enableRole(@Valid EnableDto dto) {
        roleService.enableRole(dto);
        return commonResult();
    }

    @PostMapping("/delete")
    public Object deleteRole(@Valid DeleteDto dto) {
        roleService.deleteRole(dto.getId());
        return commonResult();
    }

    @PostMapping("/setPermission")
    public Object setRolePermission(@Valid RolePerSetDto dto) {
        roleService.setRolePermission(dto);
        return commonResult();
    }
}
