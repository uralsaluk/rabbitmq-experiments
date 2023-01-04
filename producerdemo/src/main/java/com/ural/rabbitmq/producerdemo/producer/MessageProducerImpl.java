package com.ural.rabbitmq.producerdemo.producer;

import com.ural.rabbitmq.producerdemo.config.AMQPConfiguration;
import com.ural.rabbitmq.producerdemo.model.TestMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MessageProducerImpl implements  MessageProducer {


    private RabbitTemplate rabbitTemplate;

    @Autowired
    public MessageProducerImpl(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @Override
    public void produce(TestMessageDTO message) {

        message.setId(UUID.randomUUID().toString());

        rabbitTemplate.convertAndSend(AMQPConfiguration.EXCHANGE,AMQPConfiguration.ROUTING_KEY,message);


    }

    @Override
    public TestMessageDTO produceAndGetResult(TestMessageDTO message) {

        message.setId(UUID.randomUUID().toString());

        TestMessageDTO responseMessage = rabbitTemplate
                .convertSendAndReceiveAsType(AMQPConfiguration.RPC_EXCHANGE,
                        AMQPConfiguration.RPC_ROUTING_KEY,
                        message,
                        new ParameterizedTypeReference<TestMessageDTO>() {
                        });

        return responseMessage;
    }
}
