package com.chen.test;

import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class MyClient implements Watcher, Runnable {

	private final static String root = "/test";
	private final static String name = root + "/server";

	private static ZooKeeper zk;
	private String myZnode;
	
	public static ZooKeeper getZk() {
		return zk;
	}

	//注解序号为启动运行顺序
	public MyClient() throws IOException, KeeperException, InterruptedException {
		// 1. 创建新的客户端
		zk = new ZooKeeper("localhost:2182", 3000, this);

		// 3. 从Callback回来
		if (zk.exists(root, true) == null) {
    		zk.create(root, "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
    	}
		// 4. 创建此client的临时节点
		myZnode = zk.create(name, "server".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
		// 5. 设置此临时节点的watcher
		zk.exists(myZnode, true);
		// 6. 设置对/test节点的
		zk.getChildren(root, true);
	}

	@Override
	public void process(WatchedEvent event) {
		//2. keeperState的event
		System.out.println("触发了事件路径："+event.getPath());
		try {
			zk.exists(myZnode, true);
			zk.getChildren(root, true);
		} catch (KeeperException | InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			synchronized (this) {
				while (true) {
					wait();
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}


	public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
		new MyClient().run();
	}
}
