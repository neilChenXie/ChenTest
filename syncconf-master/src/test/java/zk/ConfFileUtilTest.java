package zk;

import org.junit.Test;

import zk.sync.ConfFileUtil;

public class ConfFileUtilTest {

	@Test
	public void testWriteFile() {
		String content = "sync content";
		ConfFileUtil.sync(content.getBytes());
	}

}
