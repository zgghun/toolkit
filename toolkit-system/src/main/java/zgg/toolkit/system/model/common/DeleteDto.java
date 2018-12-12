package zgg.toolkit.system.model.common;

import lombok.Data;
import zgg.toolkit.system.enums.StatusEnum;

import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/10/19
 */
@Data
public class DeleteDto {
    @NotNull
    private Long id;
    @NotNull
    private StatusEnum status;
}
