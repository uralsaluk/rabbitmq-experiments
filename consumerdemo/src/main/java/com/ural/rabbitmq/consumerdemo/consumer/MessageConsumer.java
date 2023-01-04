package com.ural.rabbitmq.consumerdemo.consumer;

import com.rabbitmq.client.Channel;
import com.ural.rabbitmq.consumerdemo.model.TestMessageDTO;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

@Component
public class MessageConsumer {
    public static final String QUEUE = "testQueue";
    public static final String EXCHANGE = "testExchange";
    public static final String ROUTING_KEY = "testKey";
    public static final String RPC_QUEUE = "testRPCQueue";
    public static final String RPC_EXCHANGE = "testRPCExchange";
    public static final String RPC_ROUTING_KEY = "";

    private Integer counter =0;

    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageConsumer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }
    @RabbitListener(queues = QUEUE)
    public void consumeMessageQueue( TestMessageDTO testMessageDTO, Message message, Channel channel)  {


        counter++;
        System.out.println(testMessageDTO);
        System.out.println(message);
        System.out.println(channel);

    }


    @RabbitListener(queues = RPC_QUEUE)
    public TestMessageDTO rpcMessage(@Payload TestMessageDTO testMessageDTO, Message message, Channel channel)  {

        testMessageDTO.getDetail().setMessage("RESPONSE SUCCESSS");


        return testMessageDTO;
    }


  /*  public void listen(Message message, Channel channel) throws IOException {
        Log log = null;
        System.out.println("consume first");

        try {
            log = objectMapper.readValue(message.getBody(), Log.class);
            System.out.println("test");
            System.out.println(log.toString());
            logRepository.save(log);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            System.out.println("consume last");
        } catch (Exception e) {
            channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);

        }*/

}
