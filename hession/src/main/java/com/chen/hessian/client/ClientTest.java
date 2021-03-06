package com.chen.hessian.client;

import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.caucho.hessian.client.HessianProxyFactory;
import com.chen.hessian.api.HessianApi;

public class ClientTest{

	public static String url = "http://127.0.0.1:8086/hession/hessian";

	public static void main(String[] args) {
//		HessianProxyFactory hFactory = new HessianProxyFactory();
//		try{
//			HessianApi hApi = (HessianApi) hFactory.create(HessianApi.class,url);
//			System.out.println(hApi.say());
//		} catch(MalformedURLException e) {
//			e.printStackTrace();
//		}
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:remote-client.xml");
		HessianApi hApi =(HessianApi) ac.getBean("clientSpring");
		System.out.println(hApi.say());
	}

}
