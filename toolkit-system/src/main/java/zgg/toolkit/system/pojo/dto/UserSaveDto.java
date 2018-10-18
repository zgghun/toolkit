package zgg.toolkit.system.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * Created by zgg on 2018/10/18
 */
@Data
public class UserSaveDto {
    private Long id;
    private String username;
    private String tel;

    private String email;
    private String password;
    private String avatar;

    private String gender;
    private String status;

    private Date createTime;
    private Date updateTime;
}
