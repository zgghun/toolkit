package zgg.toolkit.system.model.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by zgg on 2018/10/26
 */
@Data
public class LoginDto {
    @NotBlank
    private String username;
    @NotBlank
    private String password;

    private String captcha;

}
