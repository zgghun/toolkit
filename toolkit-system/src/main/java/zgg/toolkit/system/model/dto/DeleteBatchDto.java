package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * Created by zgg on 2018/10/19
 */
@Data
public class DeleteBatchDto {
    @NotEmpty
    private List<Long> ids;
}
