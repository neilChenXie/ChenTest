package com.chen.eg2;


public class Demo {
	
	public static void main(String[] args) {
		KafkaSimpleProducer producerThread = new KafkaSimpleProducer(KafkaProperties.topic2);
		producerThread.start();
	}

}
