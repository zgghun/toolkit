package zgg.toolkit.system.model.vo;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.system.model.entity.Permission;

import java.util.List;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class PermissionVo {
    private Long id;
    private Long pid;

    private String moduleName;
    private String moduleCode;
    private String icon;
    private Integer sort;
    private StatusEnum status;

    private List<Permission> permissions;

    private List<PermissionVo> children;
}
