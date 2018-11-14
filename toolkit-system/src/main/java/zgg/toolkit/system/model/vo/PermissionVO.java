package zgg.toolkit.system.model.vo;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;
import zgg.toolkit.core.model.MapVO;

import java.util.List;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class PermissionVO {
    private String moduleCode;
    private String moduleName;
    private String parentModule;

    private String icon;
    private Integer sort;
    private StatusEnum status;

    private List<MapVO> permissions;

    private List<PermissionVO> children;
}
