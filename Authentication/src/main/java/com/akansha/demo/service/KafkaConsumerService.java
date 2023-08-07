package com.akansha.demo.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

	@KafkaListener(topics="javamc", groupId="mygroup")
	public void consumeFromTopic(String message) {
		System.out.println("Consumer message: KAFKA "+ message);
	}
}
