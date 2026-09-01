package cc.hongz.ztools.setting;

import cc.hongz.ztools.setting.dialect.PropsUtil;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Objects;

@DisplayName("PropsUtil 工具类")
public class PropsUtilTest {

    @Test
    @DisplayName("按名称获取 Props")
    public void getTest() {
        String driver = PropsUtil.get("test").getStr("driver");
        assertEquals("com.mysql.jdbc.Driver", driver);
    }

    @Test
    @DisplayName("获取第一个存在的 Props")
    public void getFirstFoundTest() {
        String driver = Objects.requireNonNull(PropsUtil.getFirstFound("test2", "test")).getStr("driver");
        assertEquals("com.mysql.jdbc.Driver", driver);
    }
}
