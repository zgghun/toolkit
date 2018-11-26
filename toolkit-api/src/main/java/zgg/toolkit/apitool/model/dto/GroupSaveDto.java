package zgg.toolkit.apitool.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/26
 */
@Data
public class GroupSaveDto {
    private Long id;
    @NotNull
    private Long projectId;
    @NotBlank
    private String name;
    @NotNull
    private Integer sort;
}
