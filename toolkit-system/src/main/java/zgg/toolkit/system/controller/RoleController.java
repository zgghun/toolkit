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
public class RoleController extends SystemBaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/find")
    public Object findRole(RoleQuery query, PageParam pageParam){
        PageList<Role> list = roleService.findRole(query, pageParam);
        return commonResult(list);
    }

    @PostMapping("/delete")
    public Object deleteRole(@Valid DeleteBatchDto dto){
        roleService.deleteRole(dto.getIds());
        return commonResult();
    }

    @PostMapping("/save")
    public Object saveRole(@Valid RoleSaveDto dto){
        Role role = roleService.saveRole(dto);
        return commonResult(role);
    }

    @PostMapping("/setPermission")
    public Object setRolePermission(@Valid RolePerSetDto dto){
        roleService.setRolePermission(dto);
        return commonResult();
    }
}
