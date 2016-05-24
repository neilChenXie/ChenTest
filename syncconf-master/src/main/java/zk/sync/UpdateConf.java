package zk.sync;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.junit.Test;

public class UpdateConf {

	@Test
	public void setZKDataByConf() {

		File file = new File(Constant.YARN_SITE);
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			StringBuilder sb = new StringBuilder();
			while ((tempString = reader.readLine()) != null) {
				sb.append(tempString + "\n");
			}
			byte[] bytes = sb.toString()
					.substring(0, sb.toString().length() - 1).getBytes();

			Watcher nullWatcher = new Watcher() {
				@Override
				public void process(WatchedEvent event) {
				}
			};

			ZooKeeper zk = new ZooKeeper(Constant.ZKPEER, 30000, nullWatcher);
			zk.exists(Constant.ZNODE, false);
			zk.setData(Constant.ZNODE, bytes, -1, null, null);

			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	@Test
	public void resetZnodeData() throws Exception {

		Watcher nullWatcher = new Watcher() {
			@Override
			public void process(WatchedEvent event) {
			}
		};
		ZooKeeper zk = new ZooKeeper(Constant.ZKPEER, 30000, nullWatcher);
		zk.exists(Constant.ZNODE, false);
		zk.setData(Constant.ZNODE, "reset".getBytes(), -1, null, null);

	}

}
