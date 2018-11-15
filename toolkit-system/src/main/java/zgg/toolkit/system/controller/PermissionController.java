package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.ModuleSaveDto;
import zgg.toolkit.system.model.dto.ModuleUpdateDto;
import zgg.toolkit.system.model.dto.PermissionUpdateDto;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.vo.PermissionVO;
import zgg.toolkit.system.service.PermissionService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zgg on 2018/10/26
 * 模块和权限是在一张表，模块仅仅用于对权限分组，便于查看、理解
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController extends SystemBaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("")
    public Object findPermission(){
        List<PermissionVO> perVO = permissionService.findPermission();
        return commonResult(perVO);
    }

    @PostMapping("/addModule")
    public Object addModule(@Valid ModuleSaveDto dto){
        Permission permission = permissionService.saveModule(dto);
        return commonResult(permission);
    }

    @PostMapping("/updateModule")
    public Object updateModule(@Valid ModuleUpdateDto dto){
        permissionService.updateModule(dto);
        return commonResult();
    }


    @PostMapping("/updatePer")
    public Object updatePermission(@Valid PermissionUpdateDto dto){
        permissionService.updatePermission(dto);
        return commonResult();
    }



}
