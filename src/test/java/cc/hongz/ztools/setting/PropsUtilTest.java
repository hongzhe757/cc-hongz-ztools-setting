package cc.hongz.ztools.setting;

import cc.hongz.ztools.setting.dialect.PropsUtil;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.Objects;

public class PropsUtilTest {

	@Test
	public void getTest() {
		String driver = PropsUtil.get("test").getStr("driver");
		assertEquals("com.mysql.jdbc.Driver", driver);
	}

	@Test
	public void getFirstFoundTest() {
		String driver = Objects.requireNonNull(PropsUtil.getFirstFound("test2", "test")).getStr("driver");
		assertEquals("com.mysql.jdbc.Driver", driver);
	}
}
