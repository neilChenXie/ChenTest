package com.chen.fileMonitor;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;

public class FileWatcher implements FileAlterationListener {

	@Override
	public void onDirectoryChange(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("DirChange"+arg0.getName());
	}

	@Override
	public void onDirectoryCreate(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("DirCreate"+arg0.getName());
	}

	@Override
	public void onDirectoryDelete(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("DirDel"+arg0.getName());		
	}

	@Override
	public void onFileChange(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("FileChange"+arg0.getName());		
		
	}

	@Override
	public void onFileCreate(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("FileCreate"+arg0.getName());		
	}

	@Override
	public void onFileDelete(File arg0) {
		// TODO Auto-generated method stub
		System.out.println("FileDele"+arg0.getName());
	}

	@Override
	public void onStart(FileAlterationObserver arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getName());
		System.out.println("start listen");
	}

	@Override
	public void onStop(FileAlterationObserver arg0) {
		// TODO Auto-generated method stub
		//System.out.println(arg0.getName());
		System.out.println("stop listen");
	}

}
