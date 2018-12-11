package zgg.toolkit.common.rabbitmq;

/**
 * Created by zgg on 2018/12/11
 */

public class MqConst {
    // queues, 对于direct exchange, routing key 及 name 尽可能保持一致
    public static final String EMAIL_QUEUE = "email";
    public static final String VERIFY_CODE_QUEUE = "verify_code";

    // exchanges
    public static final String DIRECT_EXCHANGE = "amq.direct";
    public static final String FANOUT_EXCHANGE = "amq.fanout";
    public static final String TOPIC_EXCHANGE = "amq.topic";
}
