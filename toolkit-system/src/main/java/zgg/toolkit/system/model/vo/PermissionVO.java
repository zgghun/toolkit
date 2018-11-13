package zgg.toolkit.system.model.vo;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

import java.util.List;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class PermissionVO {
    private Long id;
    private Long pid;
    private String moduleName;
    private String moduleCode;
    // TODO 权限要改
    private String perName;
    private String perCode;
    private String icon;
    private Integer sort;
    private StatusEnum status;

    private List<PermissionVO> children;
}
