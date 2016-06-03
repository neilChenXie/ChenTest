package com.chen.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.springframework.stereotype.Component;

@Component
public class FileListener implements FileAlterationListener {

	@Override
	public void onDirectoryChange(File file) {
		System.out.println("onDirectoryChange: " + file.toString());
	}

	@Override
	public void onDirectoryCreate(File file) {
		System.out.println("onDirectoryCreate: " + file.toString());
	}

	@Override
	public void onDirectoryDelete(File file) {
		System.out.println("onDirectoryDelete: " + file.toString());
	}

	@Override
	public void onFileChange(File file) {
		System.out.println("onFileChange: " + file.toString());
	}

	@Override
	public void onFileCreate(File file) {
		System.out.println("onFileCreate: " + file.toString());
	}

	@Override
	public void onFileDelete(File file) {
		System.out.println("onFileDelete: " + file.toString());
	}

	@Override
	public void onStart(FileAlterationObserver filealterationobserver) {
		System.out.println("onStart ob is:"  + filealterationobserver.toString());
	}

	@Override
	public void onStop(FileAlterationObserver filealterationobserver) {
		System.out.println("onStop ob is:"  + filealterationobserver.toString());
	}

}
