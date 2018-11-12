package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.core.enums.StatusEnum;

/**
 * Created by zgg on 2018/11/12
 */

@Data
public class RoleQuery {
    private String keyword;
    private StatusEnum status;
}
