package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class ModuleSaveDto {
    private Long id;
    @NotNull
    private Long pid;

    @NotBlank
    private String moduleName;
    @NotBlank
    private String moduleCode;
    @NotBlank
    private String icon;
    @NotNull
    private Integer sort;
}
