package zgg.toolkit.core.bean;

import lombok.Data;

import java.time.LocalDate;

/**
 * Created by zgg on 2018/08/29
 */

@Data()
public class User {
    private Integer id;

    private String username;
    private Integer age;
    private LocalDate birthday;

}
