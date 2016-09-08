package com.chen.hbase.util;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HBaseUtil {

	private static Logger log = LoggerFactory.getLogger(HBaseUtil.class);

	private static Configuration conf = null;

	private static Connection connection = null;

	static {
		//TODO: change to hbase-site.xml, hdfs-site.xml
		conf.set("hbase.zookeeper.quorum", "172.16.2.51");
		conf.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			connection = ConnectionFactory.createConnection(conf);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	* @describe 检查表是否存在
	*
	* @author neil_xie
	* @date Sep 8, 2016
	* @param tableName 表名
	* @return
	*/
	public static boolean isExist(String tableName) throws IOException {
		try (Admin admin = connection.getAdmin()) {
			return admin.tableExists(TableName.valueOf(tableName));
		}
	}

	/**
	* @describe 创建表
	*
	* @author neil_xie
	* @date Sep 8, 2016
	* @param 
	* 		tableName 表名
	* 		columnFamilys 列族数组
	* @return
	*/
	public static void createTable(String tableName, String[] columnFamilys) throws IOException {
		try (Admin admin = connection.getAdmin()) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				log.warn("Table: {} not existed", tableName);
			} else {
				HTableDescriptor newTable = new HTableDescriptor(TableName.valueOf(tableName));
				for (String columnFamily : columnFamilys) {
					newTable.addFamily(new HColumnDescriptor(columnFamily));
				}
				admin.createTable(newTable);
				log.info("HBase Table: {} created", tableName);
			}
		}
	}


	/**
	* @describe 删除表
	*
	* @author hadoop
	* @date Sep 8, 2016
	* @param tableName 表名
	* @return
	*/
	public static void deleteTable(String tableName) throws IOException {
		try (Admin admin = connection.getAdmin()) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				admin.disableTable(TableName.valueOf(tableName));
				admin.deleteTable(TableName.valueOf(tableName));
				log.info("HBase Table: {} deleted", tableName);
			} else {
				log.warn("Table: {} not existed", tableName);
			}
		}
	}

	/**
	* @describe 新增行，或修改某列的值
	*
	* @author neil_xie
	* @date Sep 8, 2016
	* @param
	* @return
	*/
	public static void addRow(String tableName, String rowKey, String columnFamily, String column, String value)
			throws IOException {
		try (Admin admin = connection.getAdmin(); Table table = connection.getTable(TableName.valueOf(tableName))) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				Put put = new Put(Bytes.toBytes(rowKey));
				put.addColumn(Bytes.toBytes(columnFamily), Bytes.toBytes(column), Bytes.toBytes(value));
				table.put(put);
				log.info("{action: 'row add', table:'{}', rowKey: '{}', columnFamily: '{}', column:'{}', value:'{}'}",
						tableName, rowKey, columnFamily, column, value);
			} else {
				log.warn("Table: {} not existed", tableName);
			}
		}
	}

	/**
	* @describe 删除行
	*
	* @author hadoop
	* @date Sep 8, 2016
	* @param
	* 		tableName 表名
	* 		rowKey 行键名
	* @return
	*/
	public static void delRow(String tableName, String rowKey) throws IOException {
		try (Admin admin = connection.getAdmin(); Table table = connection.getTable(TableName.valueOf(tableName))) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				Delete delete = new Delete(Bytes.toBytes(rowKey));
				table.delete(delete);
				log.info("{action:'row delete, table:'{}', rowKey: '{}'}", tableName, rowKey);

			} else {
				log.warn("Table: {} not existed", tableName);
			}
		}
	}

	/**
	 * @describe 删除多行 (Unfinished)
	 *
	 * @author neil_xie
	 * @date Sep 8, 2016
	 * @param 
	 * 		tableName 表名
	 * 		rowKeys 行键数组
	 * @return
	 * @throws IOException 
	 */
	public static void delMultiRows(String tableName, String[] rowKeys) throws IOException {
		try (Admin admin = connection.getAdmin(); Table table = connection.getTable(TableName.valueOf(tableName))) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				// TODO：
				// log.info("{action:'row delete, table:'{}', rowKey: '{}'}",
				// tableName, rowKey);

			} else {
				log.warn("Table: {} not existed", tableName);
			}
		}
	}

	/**
	* @describe 全表扫描
	*
	* @author neil_xie
	* @date Sep 8, 2016
	* @param
	* 		tableName 表名
	* @return
	*/
	public static ResultScanner getAllRows(String tableName) throws IOException {
		try (Admin admin = connection.getAdmin(); Table table = connection.getTable(TableName.valueOf(tableName))) {
			if (admin.tableExists(TableName.valueOf(tableName))) {
				Scan scan = new Scan();
				ResultScanner res = table.getScanner(scan);
				log.info("{action:'table scan, table:'{}'}", tableName);
				return res;
			} else {
				log.warn("Table: {} not existed", tableName);
				return null;
			}
		}
	}
}
