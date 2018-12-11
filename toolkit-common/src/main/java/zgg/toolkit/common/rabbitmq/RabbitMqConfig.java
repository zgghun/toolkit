package zgg.toolkit.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by zgg on 2018/12/10
 * 初始化 queues, exchanges 并且将 queues 和 exchanges 绑定
 */
@Configuration
public class RabbitMqConfig {
    @Bean
    public Queue emailQueue() {
        return new Queue(MqConst.EMAIL_QUEUE);
    }

    @Bean
    public Queue verifyCodeQueue() {
        return new Queue(MqConst.VERIFY_CODE_QUEUE);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MqConst.DIRECT_EXCHANGE);
    }

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(MqConst.FANOUT_EXCHANGE);
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(MqConst.TOPIC_EXCHANGE);
    }

    /**
     * 由于上边定义了多个Queue, 这里参数里queue的名字要保证和上边Queue的bean name一直，默认是方法名
     * 或者使用@Qualifier("emailQueue")指定注入哪个bean
     */
    @Bean
    public Binding bindingTopicExchangeWithEmailQueue(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue).to(directExchange).with(MqConst.EMAIL_QUEUE);
    }

    @Bean
    public Binding bindingTopicExchangeWithVerifyCodeQueue(@Qualifier("verifyCodeQueue") Queue queue, DirectExchange directExchange) {
        return BindingBuilder.bind(queue).to(directExchange).with(MqConst.VERIFY_CODE_QUEUE);
    }

}
