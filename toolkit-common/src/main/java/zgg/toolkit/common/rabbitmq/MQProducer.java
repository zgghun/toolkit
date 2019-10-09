package zgg.toolkit.common.rabbitmq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by zgg on 2018/12/11
 */
@Component
public class MQProducer {
    @Autowired
    private AmqpTemplate rabbitTemplate;

    /**
     * 发送邮件id 到rabbit
     */
    public void sendEmailToMq(List<Long> emailIds) {
        emailIds.forEach(id -> rabbitTemplate.convertAndSend(MQConst.DIRECT_EXCHANGE, MQConst.EMAIL_QUEUE, id));
    }

    /**
     * 发送手机验证码id 到rabbit
     */
    public void sendVerifyCodeToMq(List<Long> verifyCodeIds) {
        verifyCodeIds.forEach(id -> rabbitTemplate.convertAndSend(MQConst.DIRECT_EXCHANGE, MQConst.VERIFY_CODE_QUEUE, id));
    }

}
