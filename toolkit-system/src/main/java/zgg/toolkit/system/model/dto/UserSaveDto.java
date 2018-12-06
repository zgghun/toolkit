package zgg.toolkit.system.model.dto;

import lombok.Data;
import zgg.toolkit.system.enums.GenderEnum;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by zgg on 2018/10/18
 */
@Data
public class UserSaveDto {
    private Long id;
    @NotBlank
    private String username;
    private String tel;

    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String avatar;

    @NotNull
    private GenderEnum gender;

}
