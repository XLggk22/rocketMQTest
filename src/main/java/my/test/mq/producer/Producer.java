package my.test.mq.producer;

import java.util.Date;

import com.alibaba.rocketmq.client.exception.MQClientException;
import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.client.producer.SendResult;
import com.alibaba.rocketmq.common.message.Message;
import sun.rmi.runtime.Log;

public class Producer {
    public static void main(String[] args) throws MQClientException {
    	String topicName = "TopicA-test";
        DefaultMQProducer producer = new DefaultMQProducer("rmq-group");
        producer.setNamesrvAddr("111.231.191.92:9876");
        producer.setInstanceName("producer");
        producer.start();
        try {
            for (int i = 0; i < 10; i++) {
                Thread.sleep(1000);  //每秒发送一次MQ
                Message msg = new Message(topicName,// topic
                        "TagA",// tag
                        (new Date() + "Hello RocketMQ ,QuickStart" + i)
                                .getBytes()// body
                );
                SendResult sendResult = producer.send(msg);
                System.out.println("sendResult=====================>"+sendResult);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        producer.shutdown();
    }
}
