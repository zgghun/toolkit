package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zgg on 2018/11/12
 */

@Data
public class RolePerSetDto {
    @NotNull
    private Long roleId;
    @NotEmpty
    private List<Long> perIds;
}
