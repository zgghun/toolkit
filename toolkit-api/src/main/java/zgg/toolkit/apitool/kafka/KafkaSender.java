package zgg.toolkit.apitool.kafka;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import zgg.toolkit.common.util.DateUtils;
import zgg.toolkit.common.util.IdWorker;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by zgg on 2018/12/04
 */
//@Component
@Slf4j
public class KafkaSender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    private Gson gson = new GsonBuilder().create();

    //发送消息方法
    public void send() {
        Message message = new Message();
        message.setId(IdWorker.nextId());
        message.setMsg(UUID.randomUUID().toString().toUpperCase().replaceAll("-", ""));
        message.setSendTime(new SimpleDateFormat(DateUtils.YMDHMS).format(new Date()));
        log.info("+++++++++++++++++++++  message = {}", gson.toJson(message));
        kafkaTemplate.send("zhisheng", gson.toJson(message));
    }
}
