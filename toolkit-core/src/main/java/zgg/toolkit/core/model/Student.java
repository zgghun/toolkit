package zgg.toolkit.core.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by zgg on 2018/08/29
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    private Long id;
    private String username;
    private Integer age;

//    @DateTimeFormat(pattern = DateConsts.YMDHMS)
    private LocalDateTime localDateTime;
//    @DateTimeFormat(pattern = DateConsts.YMDHMS)
    private Date date;

}
