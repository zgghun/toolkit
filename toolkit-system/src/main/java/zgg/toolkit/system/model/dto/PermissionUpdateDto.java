package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zgg on 2018/11/15
 */

@Data
public class PermissionUpdateDto {
    @NotBlank
    private String moduleCode;
    @NotBlank
    private String perCode;
    // 存在是添加，不存在是删除
    private String perName;
}
