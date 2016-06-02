package com.chen.observer;

import org.apache.commons.io.filefilter.FileFileFilter;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.NameFileFilter;
import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FileObserver {
	private FileAlterationObserver ob = null;
	
	@Autowired
	private FileAlterationListener fl;
	
	public FileObserver() {

	}
	
	public FileObserver(String dirPath, String fileName) {
		IOFileFilter ff = FileFilterUtils.and(FileFileFilter.FILE, new NameFileFilter(fileName));
		ob = new FileAlterationObserver(dirPath, ff);
		ob.addListener(fl);
	}
}
