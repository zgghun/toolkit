package zgg.toolkit.common.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Created by zgg on 2018/12/10
 */
@Configuration
public class RabbitMqConfig {
    public static final String EMAIL_QUEUE = "email_queue";
    public static final String VERIFY_CODE_QUEUE = "verify_code_queue";

    public static final String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue emailQueue() {
        return new Queue("email_queue");
    }

    @Bean
    public Queue verifyCodeQueue() {
        return new Queue("verify_code_queue");
    }

    @Bean
    public Queue testQueue() {
        return new Queue("test_queue");
    }

    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange("topic_exchange");
    }

    /**
     * 由于上边定义了多个Queue, 这里参数里queue的名字要保证和上边Queue的bean name一直，默认是方法名
     * 或者使用@Qualifier("emailQueue")指定注入哪个bean
     */
    @Bean
    public Binding bindingTopicExchangeWithEmailQueue(Queue emailQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(emailQueue).to(topicExchange).with("email");
    }

    @Bean
    public Binding bindingTopicExchangeWithVerifyCodeQueue(Queue verifyCodeQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(verifyCodeQueue).to(topicExchange).with("verifyCode");
    }

    @Bean
    public Binding bindingTopicExchangeWithTestQueue(Queue testQueue, TopicExchange topicExchange) {
        return BindingBuilder.bind(testQueue).to(topicExchange).with("#");
    }
}
