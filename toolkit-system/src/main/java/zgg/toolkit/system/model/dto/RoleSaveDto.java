package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/10/18
 */
@Data
public class RoleSaveDto {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}
