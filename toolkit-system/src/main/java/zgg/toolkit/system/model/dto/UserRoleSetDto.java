package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by zgg on 2018/11/12
 */

@Data
public class UserRoleSetDto {
    @NotNull
    private Long userId;
    @NotEmpty
    private List<Long> roleIds;
}
