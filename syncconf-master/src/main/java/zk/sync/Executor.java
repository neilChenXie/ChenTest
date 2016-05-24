package zk.sync;

import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher, Runnable,
		DataMonitor.DataMonitorListener {

	String znode;
	DataMonitor dm;
	ZooKeeper zk;
	String filename;

	public Executor(String hostPort, String znode, String filename)
			throws KeeperException, IOException {
		this.filename = filename;
		zk = new ZooKeeper(hostPort, 3000, this);
		dm = new DataMonitor(zk, znode, this);
	}

	public static void main(String[] args) {
		args = new String[3];
		args[0] = Constant.ZKPEER;
		args[1] = Constant.ZNODE;
		args[2] = "";
//		if (args.length < 4) {
//			System.err
//					.println("USAGE: Executor hostPort znode filename program [args ...]");
//			System.exit(2);
//		}
		String hostPort = args[0];
		String znode = args[1];
		String filename = args[2];
		try {
			new Executor(hostPort, znode, filename).run();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void process(WatchedEvent event) {
		dm.process(event);
	}

	public void run() {
		try {
			synchronized (this) {
				while (!dm.dead) {
					wait();
				}
			}
		} catch (InterruptedException e) {
		}
	}

	public void closing(int rc) {
		synchronized (this) {
			notifyAll();
		}
	}

	/**
	 * 处理数据
	 */
	public void exists(byte[] data) {

		System.out.println("...Invoked:Executor.exists(byte[] data)...");
		ConfFileUtil.sync(data);
	}
}