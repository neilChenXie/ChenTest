package com.chen.observer;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

@Component
public class ObserverFactory {
	
	public ObserverFactory() {
		
	}
	
	public static FileAlterationObserver getObserver(String dirPath, String fileName, FileAlterationListener fileListener) {
		IOFileFilter ff = FileFilterUtils.and(FileFileFilter.FILE, new NameFileFilter(fileName));
		FileAlterationObserver ob = new FileAlterationObserver(dirPath, ff);
		ob.addListener(fileListener);
		return ob;
	}
}
