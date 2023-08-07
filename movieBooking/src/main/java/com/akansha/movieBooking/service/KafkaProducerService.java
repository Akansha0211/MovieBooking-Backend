package com.akansha.movieBooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

public static final String topic = "javamc";
	
	@Autowired
	public KafkaTemplate<String, String> tempMsg;

	public KafkaTemplate<String, String> getTempMsg() {
		return tempMsg;
	}

	public void setTempMsg(String msg) {
		this.tempMsg.send(topic, msg);
	}

	public static String getTopic() {
		return topic;
	}

}
