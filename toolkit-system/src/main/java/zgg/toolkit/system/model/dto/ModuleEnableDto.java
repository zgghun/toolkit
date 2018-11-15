package zgg.toolkit.system.model.dto;

import lombok.Data;
import lombok.NonNull;
import zgg.toolkit.core.enums.StatusEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/11/15
 */

@Data
public class ModuleEnableDto {
    @NonNull
    private Long id;
    @NotNull
    private StatusEnum status;
}
