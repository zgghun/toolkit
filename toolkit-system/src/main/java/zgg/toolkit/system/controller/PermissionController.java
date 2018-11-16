package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.DeleteDto;
import zgg.toolkit.system.model.dto.ModuleEnableDto;
import zgg.toolkit.system.model.dto.ModuleSaveDto;
import zgg.toolkit.system.model.dto.PermissionSaveDto;
import zgg.toolkit.system.model.entity.Module;
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
    public Object findPermission() {
        List<PermissionVO> perVO = permissionService.findPermissionTree();
        return commonResult(perVO);
    }

    @PostMapping("/saveModule")
    public Object saveModule(@Valid ModuleSaveDto dto) {
        Module module = permissionService.saveModule(dto);
        return commonResult(module);
    }

    @PostMapping("/setModule")
    public Object setModule(@Valid ModuleEnableDto dto) {
        permissionService.setModule(dto);
        return commonResult();
    }

    @PostMapping("/deleteModule")
    public Object deleteModule(@RequestParam Long id) {
        permissionService.deleteModule(id);
        return commonResult();
    }

    @PostMapping("/savePer")
    public Object savePermission(@Valid PermissionSaveDto dto) {
        permissionService.savePermission(dto);
        return commonResult();
    }

    @PostMapping("/deletePer")
    public Object deletePermission(@Valid DeleteDto dto) {
        permissionService.deletePermission(dto.getId(), null);
        return commonResult();
    }

}
