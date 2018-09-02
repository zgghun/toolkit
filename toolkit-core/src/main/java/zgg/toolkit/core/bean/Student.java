package zgg.toolkit.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by zgg on 2018/08/29
 */

@Data
public class Student {
    private Long id;
    private String username;
    private Integer age;

    @JsonFormat(pattern = "yyyy|MM|dd HH:mm:ss")
    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date date;

    public Student() {
    }

    public Student(Long id, String username, Integer age, LocalDateTime localDateTime, LocalDate localDate, LocalTime localTime, Date date) {
        this.id = id;
        this.username = username;
        this.age = age;
        this.localDate = localDate;
        this.localDateTime = localDateTime;
        this.localTime = localTime;
        this.date = date;
    }
}
