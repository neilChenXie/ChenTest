package com.chen.fileMonitor;

import org.apache.commons.io.input.Tailer;
import org.apache.commons.io.input.TailerListener;

public class MyTailListener implements TailerListener {

	@Override
	public void fileNotFound() {
		System.out.println("not found");
	}

	@Override
	public void fileRotated() {
		System.out.println("file rotated");
	}

	@Override
	public void handle(String s) {
		System.out.println("changing");

	}

	@Override
	public void handle(Exception exception) {
		System.out.println("exp");
	}

	@Override
	public void init(Tailer tailer) {

	}

}
