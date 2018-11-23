package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/10/19
 */
@Data
public class DeleteDto {
    @NotNull
    @Min(10)
    private Long id;
    @NotNull
    private StatusEnum status;
}
