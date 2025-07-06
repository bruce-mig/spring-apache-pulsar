package com.github.bruce_mig.spring_apache_pulsar.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventPublisher {

    @Value("${spring.pulsar.producer.topic-name}")
    private String topicName;

    private final PulsarTemplate<Object> pulsarTemplate;

    public EventPublisher(PulsarTemplate<Object> pulsarTemplate) {
        this.pulsarTemplate = pulsarTemplate;
    }

    public void publishPlainMessage(String message){
        pulsarTemplate.send(topicName, message);
        log.info("EventPublisher::publishPlainMessage published the event {}", message);
    }
}
