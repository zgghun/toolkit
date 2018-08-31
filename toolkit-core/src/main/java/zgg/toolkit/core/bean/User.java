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

    public User() {
    }

    public User(Integer id, String username, Integer age, LocalDate birthday) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.birthday = birthday;
    }
}
