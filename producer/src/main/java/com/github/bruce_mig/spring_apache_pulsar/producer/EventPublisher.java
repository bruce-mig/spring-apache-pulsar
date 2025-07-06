package com.github.bruce_mig.spring_apache_pulsar.producer;

import com.github.bruce_mig.spring_apache_pulsar.commons.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.pulsar.core.PulsarTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventPublisher {

    @Value("${spring.pulsar.producer.topic-name-0}")
    private String plainTopicName;

    @Value("${spring.pulsar.producer.topic-name-1}")
    private String rawTopicName;

    private final PulsarTemplate<Object> pulsarTemplate;

    public EventPublisher(PulsarTemplate<Object> pulsarTemplate) {
        this.pulsarTemplate = pulsarTemplate;
    }

    public void publishPlainMessage(String message){
        pulsarTemplate.send(plainTopicName, message);
        log.info("EventPublisher::publishPlainMessage published the event {}", message);
    }

    public void publishRawMessage(Customer customer){
        pulsarTemplate.send(rawTopicName, customer);
        log.info("EventPublisher::publishRawMessage published the event {}", customer);
    }
}
