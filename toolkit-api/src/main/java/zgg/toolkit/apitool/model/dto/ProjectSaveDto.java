package zgg.toolkit.apitool.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zgg on 2018/11/26
 */
@Data
public class ProjectSaveDto {
    private Long id;
    @NotBlank
    private String name;
    private String note;
}
