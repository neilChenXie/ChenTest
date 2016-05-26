package zk.sync;

import java.io.FileOutputStream;
import java.io.IOException;

public class ConfFileUtil {

	/**
	 * 同步本地配置文件
	 * 
	 * @param data
	 * @return
	 */
	public static boolean sync(byte[] data) {

		if (data != null) {
			ConfFileUtil.writeToBytes(data, Constant.SYNC_FILE);
		}
		return true;
	}

	public static void writeToBytes(byte bytes[], String fileName) {

		FileOutputStream fos = null;

		try {
			fos = new FileOutputStream(fileName, false);
			fos.write(bytes);
			fos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
