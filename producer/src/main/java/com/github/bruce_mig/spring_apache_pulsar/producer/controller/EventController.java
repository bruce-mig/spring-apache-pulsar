package com.github.bruce_mig.spring_apache_pulsar.producer.controller;

import com.github.bruce_mig.spring_apache_pulsar.commons.dto.Customer;
import com.github.bruce_mig.spring_apache_pulsar.producer.EventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/producer")
public class EventController {

    private final EventPublisher publisher;

    public EventController(EventPublisher publisher) {
        this.publisher = publisher;
    }

    @GetMapping("/text/{message}")
    public ResponseEntity<String> sendTextEvent(@PathVariable String message){
        publisher.publishPlainMessage(message);
        return ResponseEntity.accepted().body("Message Published");
    }

    @PostMapping("/raw")
    public ResponseEntity<String> sendRawEvent(@RequestBody Customer customer){
        publisher.publishRawMessage(customer);
        return ResponseEntity.accepted().body("Custom object Published");
    }
}
