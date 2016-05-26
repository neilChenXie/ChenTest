package com.chen;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class MyClient implements Watcher {

	private final static String root = "/test";
	private final static String name = root + "/server";

	private static ZooKeeper zk;
	
	public static ZooKeeper getZk() {
		return zk;
	}

	public MyClient() throws IOException {
		// TODO Auto-generated constructor stub
		zk = new ZooKeeper("localhost:2182", 3000, this);
	}

	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println("触发了事件路径："+event.getPath());
	}

	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		MyClient mc = new MyClient();
		
		if (MyClient.getZk().exists(root, true) == null) {
    		MyClient.getZk().create(root, "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
		if (MyClient.getZk().exists(name, true) == null) {
    		MyClient.getZk().create(name, "server".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
    	}
	}
}
