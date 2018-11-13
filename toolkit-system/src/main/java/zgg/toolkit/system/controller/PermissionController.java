package zgg.toolkit.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zgg.toolkit.system.base.SystemBaseController;
import zgg.toolkit.system.model.dto.DeleteBatchDto;
import zgg.toolkit.system.model.dto.PermissionSaveDto;
import zgg.toolkit.system.model.entity.Permission;
import zgg.toolkit.system.model.vo.PermissionVO;
import zgg.toolkit.system.service.PermissionService;

import javax.validation.Valid;
import java.util.List;

/**
 * Created by zgg on 2018/10/26
 */
@RestController
@RequestMapping("/sys/permission")
public class PermissionController extends SystemBaseController {
    @Autowired
    private PermissionService permissionService;

    @GetMapping("/find")
    public Object findPermission(String moduleName){
        List<PermissionVO> perVO = permissionService.findPermission(moduleName);
        return commonResult(perVO);
    }

    @PostMapping("/save")
    public Object savePermission(@Valid PermissionSaveDto dto){
        Permission permission = permissionService.savePermission(dto);
        return commonResult(permission);
    }

    @PostMapping("/delete")
    public Object deletePermission(@Valid DeleteBatchDto dto){
        permissionService.deletePermission(dto.getIds());
        return commonResult();
    }

}
