package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/15
 */

@Data
public class ModuleUpdateDto {
    @NotBlank
    private String oldModuleCode;
    @NotBlank
    private String moduleName;
    @NotBlank
    private String moduleCode;
    @NotBlank
    private String parentModule;

    @NotBlank
    private String icon;
    @NotNull
    private Integer sort;
}
