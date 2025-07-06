package com.github.bruce_mig.spring_apache_pulsar.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.bruce_mig.spring_apache_pulsar.commons.dto.Customer;
import lombok.extern.slf4j.Slf4j;
import org.apache.pulsar.client.api.SubscriptionType;
import org.apache.pulsar.common.schema.SchemaType;
import org.springframework.pulsar.annotation.PulsarListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EventConsumer {

    @PulsarListener(
            topics = "${spring.pulsar.consumer.topic-name-0}",
            subscriptionName = "spring.pulsar.consumer.subscription.name",
            subscriptionType = SubscriptionType.Shared
    )
    public void consumeTextEvent(String message){
        log.info("EventConsumer:: consumeTextEvent consumed events {}", message);
    }

    @PulsarListener(
            topics = "${spring.pulsar.consumer.topic-name-1}",
            subscriptionName = "spring.pulsar.consumer.subscription.name",
            schemaType = SchemaType.JSON,
            subscriptionType = SubscriptionType.Shared
    )
    public void consumeRawEvent(Customer customer) throws JsonProcessingException {
        log.info("EventConsumer:: consumeRawEvent consumed events {}", new ObjectMapper().writeValueAsString(customer));
    }
}
