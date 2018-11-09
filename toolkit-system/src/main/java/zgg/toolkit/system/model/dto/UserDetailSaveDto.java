package zgg.toolkit.system.model.dto;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by zgg on 2018/11/07
 */
@Data
public class UserDetailSaveDto {
    private Long id;
    private String realName;
    private LocalDate birthday;
}
