package com.chen.test;



import java.io.IOException;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;

/**
 * Hello world!
 *
 */
public class SetConfig implements Watcher
{
	public static String url="172.16.9.55:2182";
	private final static String root = "/myConf";
	
	private final static String UrlNode = root + "/url";
	private final static String userNameNode = root + "/username";
	private final static String passWdNode = root + "/passwd";

	private final static String auth_type = "digest";
	private final static String auth_passwd = "password";

	private final static String URLString = "10.11.1.1";
	private final static String UserName = "username123";
	private final static String Passwd = "password123";
	
	private ZooKeeper zk;

	public SetConfig(String hostPort) throws IOException {
		this.zk = new ZooKeeper(hostPort, 3000, this);
	}
	
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
    			System.out.println("触发了事件路径："+event.getPath());
	}

    public static void main( String[] args ) throws Exception
    {
//    	ZooKeeper zk = new ZooKeeper(url, 3000, new Watcher() {
//    		@Override
//    		public void process(WatchedEvent event) {
//    			System.out.println("触发了事件："+event.getType());
//    		}
//    	});
    	
    	SetConfig sc = new SetConfig(url);
    	ZooKeeper zk = sc.zk;
    	
    	while (ZooKeeper.States.CONNECTED != zk.getState()) {
    		Thread.sleep(3000);
    	}

    	zk.addAuthInfo(auth_type, auth_passwd.getBytes());

    	if (zk.exists(root, true) == null) {
    		zk.create(root, "root".getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    	}
    	if (zk.exists(UrlNode, true) == null) {
    		zk.create(UrlNode, URLString.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    	}
    	if (zk.exists(userNameNode, true) == null) {
    		zk.create(userNameNode, UserName.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    	}
    	if (zk.exists(passWdNode, true) == null) {
    		zk.create(passWdNode, Passwd.getBytes(), Ids.CREATOR_ALL_ACL, CreateMode.PERSISTENT);
    	}
    	
    }
}
