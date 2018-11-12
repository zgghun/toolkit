package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/12
 */
@Data
public class PermissionSaveDto {
    private Long id;
    @NotNull
    private Long pid;

    @NotBlank
    private String moduleName;
    @NotBlank
    private String modeleCode;
    @NotBlank
    private String perName;
    @NotBlank
    private String perCode;
    @NotBlank
    private String icon;
    @NotBlank
    private Integer sort;
    @NotBlank
    private StatusEnum status;
}
