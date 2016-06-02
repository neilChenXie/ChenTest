package com.chen;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.chen.observer.FileObserver;

/**
 * Hello world!
 *
 */
public class Main 
{
    public static void main( String[] args )
    {
        ApplicationContext ac = new ClassPathXmlApplicationContext("spring.xml");
        FileAlterationMonitor monitor = (FileAlterationMonitor) ac.getBean("FileMonitor");
        monitor.run();
    }
}
