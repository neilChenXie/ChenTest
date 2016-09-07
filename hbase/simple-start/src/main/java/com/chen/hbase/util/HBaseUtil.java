package com.chen.hbase.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

public class HBaseUtil {
	
	private static Configuration conf = null;

	private static Connection connection = null;

	static {
		conf.set("hbase.zookeeper.quorum", "172.16.2.51");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			connection = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static boolean isExist(String tableName) throws IOException {
		try (Admin admin = connection.getAdmin()) {
			return admin.tableExists(TableName.valueOf(tableName));
		}
	}

	public static void createTable(String tableName, String[] columnFamilys) throws IOException {
		try (Admin admin = connection.getAdmin()) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				System.out.println("");
			}
		}
	}
}
