package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zgg.toolkit.system.base.BaseController;
import zgg.toolkit.system.model.dto.DeleteDto;
import zgg.toolkit.system.model.dto.EnableDto;
import zgg.toolkit.system.model.dto.ModuleSaveDto;
import zgg.toolkit.system.model.dto.PermissionSaveDto;
import zgg.toolkit.system.model.entity.Module;
import zgg.toolkit.system.model.vo.PermissionVo;
import zgg.toolkit.system.service.PermissionService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zgg on 2018/10/26
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController extends BaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/find")
    public Object findPermission() {
        List<PermissionVo> perVO = permissionService.findPermissionTree();
        return commonResult(perVO);
    }

    @PostMapping("/saveModule")
    public Object saveModule(@Valid ModuleSaveDto dto) {
        Module module = permissionService.saveModule(dto);
        return commonResult(module);
    }

    @PostMapping("/enableModule")
    public Object enableModule(@Valid EnableDto dto) {
        permissionService.enableModule(dto);
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
