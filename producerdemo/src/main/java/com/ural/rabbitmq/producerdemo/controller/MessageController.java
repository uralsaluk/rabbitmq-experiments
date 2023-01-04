package com.ural.rabbitmq.producerdemo.controller;

import com.ural.rabbitmq.producerdemo.model.TestMessageDTO;
import com.ural.rabbitmq.producerdemo.producer.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v0/message")
public class MessageController {


    private MessageProducer messageProducer;
    @Autowired
    public MessageController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }


    //@Async
    @RequestMapping(value="/sendAsync", method = RequestMethod.POST)
    public void sendMessageAsync(@RequestBody TestMessageDTO messageDTO){

        messageProducer.produce(messageDTO);

    }

    @RequestMapping(value="/send", method = RequestMethod.POST)
    public ResponseEntity<TestMessageDTO> sendMessage(@RequestBody TestMessageDTO messageDTO){

        TestMessageDTO responseMessage = messageProducer.produceAndGetResult(messageDTO);

        return new ResponseEntity<>(responseMessage, HttpStatus.OK);

    }

}
