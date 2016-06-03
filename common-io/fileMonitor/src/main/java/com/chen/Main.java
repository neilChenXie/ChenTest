package com.chen;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class Main 
{
    private static ApplicationContext ac;

	public static void main( String[] args ) throws Exception
    {
        ac = new ClassPathXmlApplicationContext("spring.xml");
        FileAlterationMonitor monitor = (FileAlterationMonitor) ac.getBean("FileMonitor");
        monitor.start();
    }
}
