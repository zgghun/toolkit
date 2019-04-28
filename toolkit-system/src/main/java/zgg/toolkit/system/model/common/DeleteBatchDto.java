package zgg.toolkit.system.model.common;

import lombok.Data;
import zgg.toolkit.system.enums.StatusEnum;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zgg on 2018/10/19
 * <p>通用批量删除 Dto</p>
 */
@Data
public class DeleteBatchDto {
    @NotEmpty
    private List<Long> ids;
    @NotNull
    private StatusEnum status;
}
