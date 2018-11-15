package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class ModuleAddDto {
    @NotBlank
    private String parentModule;

    @NotBlank
    private String moduleName;
    @NotBlank
    private String moduleCode;
    @NotBlank
    private String icon;
    @NotNull
    private Integer sort;
    @NotNull
    private StatusEnum status;
}
