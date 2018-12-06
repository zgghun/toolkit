package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.system.enums.GenderEnum;
import zgg.toolkit.system.enums.StatusEnum;

import java.time.LocalDateTime;

/**
 * Created by zgg on 2018/11/07
 */
@Data
public class UserQuery {
    private String keyword;
    private GenderEnum gender;
    private StatusEnum status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
