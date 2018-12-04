package zgg.toolkit.apitool.kafka;

import lombok.Data;

/**
 * Created by zgg on 2018/12/04
 */

@Data
public class Message {
    private Long id;
    private String msg;
    private String sendTime;
}
