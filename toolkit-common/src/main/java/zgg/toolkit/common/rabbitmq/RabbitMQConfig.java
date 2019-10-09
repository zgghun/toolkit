package zgg.toolkit.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by zgg on 2018/12/10
 * 初始化 queues, exchanges 并且将 queues 和 exchanges 绑定
 */
@Configuration
public class RabbitMQConfig {
    @Autowired
    private ConnectionFactory connectionFactory;

    // 开启事务回滚时，消息不发送
    @Bean
    public AmqpTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setChannelTransacted(true);
        return template;
    }

    @Bean
    public Queue emailQueue() {
        return new Queue(MQConst.EMAIL_QUEUE);
    }

    @Bean
    public Queue verifyCodeQueue() {
        return new Queue(MQConst.VERIFY_CODE_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MQConst.DIRECT_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MQConst.FANOUT_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MQConst.TOPIC_EXCHANGE);
    }

    /**
     * 由于上边定义了多个Queue, 这里参数里queue的名字要保证和上边Queue的bean name一直，默认是方法名
     * 或者使用@Qualifier("emailQueue")指定注入哪个bean
     */
    @Bean
    public Binding bindingTopicExchangeWithEmailQueue(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue).to(directExchange).with(MQConst.EMAIL_QUEUE);
    }

    @Bean
    public Binding bindingTopicExchangeWithVerifyCodeQueue(@Qualifier("verifyCodeQueue") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(MQConst.VERIFY_CODE_QUEUE);
    }

}
