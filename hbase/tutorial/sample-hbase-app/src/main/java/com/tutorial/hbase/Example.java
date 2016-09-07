package com.tutorial.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HConstants;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.io.compress.Compression.Algorithm;
import org.apache.hadoop.hbase.util.Bytes;

import com.sun.tools.classfile.StackMapTable_attribute.same_locals_1_stack_item_frame;

public class Example {

	private static final String TABLE_NAME = "chen";
	private static final String CF_DEFAULT = "NEW";
	private static Connection connection;

	public Example() {
		Configuration config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "172.16.2.51");
		config.set("hbase.zookeeper.property.clientPort", "2181");
		try {
			connection = ConnectionFactory.createConnection(config);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void createOrOverwrite(Admin admin, HTableDescriptor table) throws IOException {
		if (admin.tableExists(table.getTableName())) {
			admin.disableTable(table.getTableName());
			admin.deleteTable(table.getTableName());
		}
		admin.createTable(table);
	}

	public static void createSchemaTables(Configuration config) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {

			HTableDescriptor table = new HTableDescriptor(TableName.valueOf(TABLE_NAME));
			table.addFamily(new HColumnDescriptor(CF_DEFAULT).setCompressionType(Algorithm.SNAPPY));

			System.out.print("Creating table. ");
			createOrOverwrite(admin, table);
			System.out.println(" Done.");
		}
	}

	public  void checkInfo() throws IOException {

		// try(HConnection connection =
		// HConnectionManager.createConnection(config);
		// Admin admin = connection.getAdmin()) {
		//
		// //TableName tableName = TableName.valueOf(TABLE_NAME);
		// //HTableDescriptor table = new HTableDescriptor(tableName);
		// //System.out.println(table.getNameAsString());
		// // HColumnDescriptor newFamily = new HColumnDescriptor("DO");
		// // table.addFamily(newFamily);
		// // Boolean res = table.hasFamily(newFamily.toByteArray());
		// // table.addFamily(newFamily);
		// // admin.modifyTable(tableName, table);
		// // HColumnDescriptor[] c = table.getColumnFamilies();
		//
		//// Table tableRun = connection.getTable(tableName);
		//// Get g = new Get("1".getBytes());
		//// Result r = tableRun.get(g);
		//// r.getColumn("DO".getBytes(), qualifier);
		//// HTablePool
		//// System.out.println("toExit");
		//
		// // HColumnDescriptor existingColumn = new
		// // HColumnDescriptor(CF_DEFAULT);
		// // existingColumn.setCompactionCompressionType(Algorithm.GZ);
		// // existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
		//
		// }
		// ExecutorService executor = Executors.newFixedThreadPool(10);
		Table table1 = connection.getTable(TableName.valueOf("chen"));
		Table table2 = connection.getTable(TableName.valueOf("xie"));

		Get get1 = new Get(Bytes.toBytes("1"));
		Result r1 = table1.get(get1);
		Get get2 = new Get(Bytes.toBytes("row_1"));
		Result r2 = table2.get(get2);
		System.out.println("to close");
		table1.close();
		table2.close();
	}

	public static void modifySchema(Configuration config) throws IOException {
		try (Connection connection = ConnectionFactory.createConnection(config); Admin admin = connection.getAdmin()) {

			TableName tableName = TableName.valueOf(TABLE_NAME);
			if (!admin.tableExists(tableName)) {
				System.out.println("Table does not exist.");
				System.exit(-1);
			}

			HTableDescriptor table = new HTableDescriptor(tableName);

			// Update existing table
			// if (!table.("NEWCF".getBytes())) {
			// HColumnDescriptor newColumn = new HColumnDescriptor("NEWCF");
			// newColumn.setCompactionCompressionType(Algorithm.GZ);
			// newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			// admin.addColumn(tableName, newColumn);
			// }
			// HColumnDescriptor newColumn = new HColumnDescriptor(CF_DEFAULT);
			// newColumn.setCompactionCompressionType(Algorithm.GZ);
			// newColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			// admin.addColumn(tableName, newColumn);

			// Update existing column family
			// System.out.println('\t'+"has family NEW? " +
			// table.hasFamily(newColumn.toByteArray()));
			HColumnDescriptor existingColumn = new HColumnDescriptor(CF_DEFAULT);
			existingColumn.setCompactionCompressionType(Algorithm.GZ);
			existingColumn.setMaxVersions(HConstants.ALL_VERSIONS);
			for (HColumnDescriptor cf : table.getColumnFamilies()) {
				System.out.println(cf.getName());
			}
			table.modifyFamily(existingColumn);
			admin.modifyTable(tableName, table);

			// Disable an existing table
			admin.disableTable(tableName);

			// Delete an existing column family
			admin.deleteColumn(tableName, CF_DEFAULT.getBytes("UTF-8"));

			// Delete a table (Need to be disabled first)
			admin.deleteTable(tableName);
		}
	}

	public static void main(String... args) throws IOException {
		Example eg = new Example();
		eg.checkInfo();
	}
}