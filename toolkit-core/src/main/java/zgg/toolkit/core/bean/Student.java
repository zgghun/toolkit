package zgg.toolkit.core.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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

    @JsonFormat(pattern = "yyyy|MM|dd HH:mm:ss")
    private LocalDateTime localDateTime;

}
