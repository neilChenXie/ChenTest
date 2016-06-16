/**
 * 
 */
package com.chen;

import java.util.List;
import java.util.Map;

import org.apache.flume.Context;
import org.apache.flume.Event;
import org.apache.flume.interceptor.Interceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @describe
*
* @author Chen Xie
* @date Jun 16, 2016
*/
public class SimpleInterceptor implements Interceptor {

	private static final Logger logger = LoggerFactory.getLogger(SimpleInterceptor.class);
	/* 
	 * @see org.apache.flume.interceptor.Interceptor#close()
	 */
	@Override
	public void close() {

	}

	/* 
	 * @see org.apache.flume.interceptor.Interceptor#initialize()
	 */
	@Override
	public void initialize() {

	}

	/* 
	 * @see org.apache.flume.interceptor.Interceptor#intercept(org.apache.flume.Event)
	 */
	@Override
	public Event intercept(Event event) {
		logger.info("!!!!enter intercept!!!!!");
		Map<String, String> headers = event.getHeaders();
		headers.put("chenxie", "hello world");
		return event;
	}

	/* 
	 * @see org.apache.flume.interceptor.Interceptor#intercept(java.util.List)
	 */
	@Override
	public List<Event> intercept(List<Event> events) {
		logger.info("!!!!enter intercept list!!!!");
		for (Event event : events) {
			intercept(event);
		}
		return events;
	}
	
	public static class Builder implements Interceptor.Builder
	{

		@Override
		public void configure(Context context) {

		}

		@Override
		public Interceptor build() {
			return new SimpleInterceptor();
		}
	}
	

}
