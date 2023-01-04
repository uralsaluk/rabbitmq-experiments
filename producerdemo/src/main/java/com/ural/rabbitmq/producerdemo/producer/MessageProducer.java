package com.ural.rabbitmq.producerdemo.producer;


import com.ural.rabbitmq.producerdemo.model.TestMessageDTO;

public interface MessageProducer {

    void produce(TestMessageDTO message);

    TestMessageDTO produceAndGetResult(TestMessageDTO message);

}
