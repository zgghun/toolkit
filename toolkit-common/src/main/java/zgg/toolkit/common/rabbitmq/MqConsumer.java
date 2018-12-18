package zgg.toolkit.common.rabbitmq;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Created by zgg on 2018/12/18
 */
@Component
public class MqConsumer {

    @RabbitListener(queues = MqConst.EMAIL_QUEUE)
    @RabbitHandler
    public void process(Long id) {
        System.out.println("**********Receiver  : " + id);
    }

    @RabbitListener(queues = MqConst.VERIFY_CODE_QUEUE)
    @RabbitHandler
    public void process2(Long id) {
        System.out.println("**********Receiver  : " + id);
    }
}
