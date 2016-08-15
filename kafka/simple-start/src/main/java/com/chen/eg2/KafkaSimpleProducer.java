package com.chen.eg2;

import java.util.Properties;

import org.apache.kafka.clients.producer.Callback;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class KafkaSimpleProducer extends Thread {
	private final Producer<String, String> producer;
	private final String topic;
	private final Properties props = new Properties();

	public KafkaSimpleProducer(String topic) {
		props.put("acks", "all");
		props.put("timeout.ms", "3000");
		props.put("retries", 10000);
		props.put("group.id", "chen");
		props.put("bootstrap.servers", "172.16.9.145:9092");
		props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
		producer = new KafkaProducer<>(props);
		this.topic = topic;
	}

	public void run() {
		Integer messageNo = 1;
		while (true) {
			String messageStr = new String("Message_" + messageNo);
			System.out.println("Topic:" + topic + "Send:" + messageStr);
			try {
				ProducerRecord<String, String> record = new ProducerRecord<String, String>(topic, messageNo.toString(), messageStr);
				producer.send(record, new Callback() {
					
					@Override
					public void onCompletion(RecordMetadata metadata, Exception e) {
						if (e != null)  
	                        e.printStackTrace();  
	                    System.out.println("message send to partition " + metadata.partition() + ", offset: " + metadata.offset());  
					}
				});
				// producer.send(new ProducerRecord<String, String>(topic,
				// Integer.toString(messageNo), Integer.toString(messageNo)));
			} catch (Exception e) {
				System.out.println("send out error!!!!");
			}
			messageNo++;
			try {
				sleep(3000);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
