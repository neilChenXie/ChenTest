package com.chen.fileMonitor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileMonitor {
    
	FileAlterationMonitor monitor = null;  
    
    public FileMonitor(long interval) {  
        monitor = new FileAlterationMonitor(interval);  
    }  
  
    public void monitor(String path, FileAlterationListener listener) {  
        FileAlterationObserver observer = new FileAlterationObserver(new File(path));  
        monitor.addObserver(observer);  
        observer.addListener(listener);  
    }  
    
    public void stop() throws Exception{  
        monitor.stop();  
    }
    
    public void start() throws Exception {  
        monitor.start();  
    }
    
    public static void main(String[] args) throws Exception {  
        File directory = new File("/home/hadoop/hadoop/etc");
       // 轮询间隔 5 秒
       long interval = TimeUnit.SECONDS.toMillis(5);
       // 创建一个文件观察器用于处理文件的格式
       //FileAlterationObserver observer = new FileAlterationObserver(directory, FileFilterUtils.and(FileFilterUtils.fileFileFilter(),FileFilterUtils.suffixFileFilter(".xml")));
       FileAlterationObserver observer = new FileAlterationObserver(directory);
       //设置文件变化监听器
       observer.addListener(new FileWatcher());
       FileAlterationMonitor monitor = new FileAlterationMonitor(interval,observer);
       monitor.start();
//    	TailerListener listener = new MyTailListener();
//        Tailer tailer = Tailer.create(new File("/home/hadoop/1.xml"), listener, 500);
//        tailer.run();
    } 
}
