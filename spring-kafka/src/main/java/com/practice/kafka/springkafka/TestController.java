package com.practice.kafka.springkafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final  String topic = "KAFKA_TOPIC";
    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;


    @PostMapping("/produce")
    public void produceMsg(@RequestParam("msg") String msg,
                           @RequestParam("country") String country ){

        kafkaTemplate.send(topic,country,msg);
    }

    @KafkaListener(topics ="KAFKA_TOPIC")
    public  String consume(String msg){
        System.out.println("the message being consumed here is :- "+msg);
        return msg;
    }



}
