package zgg.toolkit.system.model.common;

import lombok.Data;
import zgg.toolkit.system.enums.StatusEnum;

import javax.validation.constraints.NotNull;

/**
 * 通用单个删除 Dto
 * @date 2018/10/19
 * @author nerve
 */
@Data
public class DeleteDto {
    @NotNull
    private Long id;
    @NotNull
    private StatusEnum status;
}
