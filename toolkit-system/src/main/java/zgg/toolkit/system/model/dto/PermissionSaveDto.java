package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/15
 */

@Data
public class PermissionSaveDto {
    private Long id;
    @NotNull
    private Long moduleId;

    @NotBlank
    private String perName;
    @NotBlank
    private String perCode;
}
