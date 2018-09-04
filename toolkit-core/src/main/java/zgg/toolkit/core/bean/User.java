package zgg.toolkit.core.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

/**
 * Created by zgg on 2018/08/29
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String username;
    private Integer age;

    private LocalDateTime localDateTime;
    private LocalDate localDate;
    private LocalTime localTime;
    private Date date;

    List<Student> stus;


}
