package com.github.bruce_mig.spring_apache_pulsar.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumer {

    @PulsarListener(
            topics = "${spring.pulsar.consumer.topic-name}",
            subscriptionName = "spring.pulsar.consumer.subscription.name",
            subscriptionType = SubscriptionType.Shared
    )
    public void consumeTextEvent(String message){
        log.info("EventConsumer:: consumeTextEvent consumed events {}", message);
    }
}
