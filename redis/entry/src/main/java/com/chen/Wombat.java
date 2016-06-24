package com.chen;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;

public class Wombat {

	final Logger logger = LoggerFactory.getLogger(Wombat.class);

	public void test() {
		logger.info("hello world");
	}

	public static void main(String[] args) {
		Jedis jedis = new Jedis("172.16.2.51");
		System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		System.out.println("Server is running: " + jedis.ping());
		jedis.set("w3ckey", "Redis tutorial");
		// 获取存储的数据并输出
		System.out.println("Stored string in redis:: " + jedis.get("w3ckey"));
	}
}
