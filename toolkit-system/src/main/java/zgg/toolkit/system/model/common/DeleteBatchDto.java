package zgg.toolkit.system.model.common;

import lombok.Data;
import zgg.toolkit.system.enums.StatusEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 通用批量删除 Dto
 * @date 2018/10/19
 * @author nerve
 */
@Data
public class DeleteBatchDto {
    @NotEmpty
    private List<Long> ids;
    @NotNull
    private StatusEnum status;
}
