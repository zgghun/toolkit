package zgg.toolkit.core.bean;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by zgg on 2018/08/29
 */

@Data
public class User {
    private Long id;
    private String username;
    private Integer age;

    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date date;

    public User() {
    }

    public User(Long id, String username, Integer age, LocalDateTime localDateTime,LocalDate localDate, LocalTime localTime, Date date) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
        this.localTime = localTime;
        this.date = date;
    }
}
