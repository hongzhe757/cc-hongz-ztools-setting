package cc.hongz.ztools.setting;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("SettingUtil 工具类")
public class SettingUtilTest {

    @Test
    @DisplayName("按名称获取 Setting")
    public void getTest() {
        String driver = SettingUtil.get("test").get("demo", "driver");
        assertEquals("com.mysql.jdbc.Driver", driver);
    }

    @Test
    @DisplayName("按路径获取 Setting")
    public void getTest2() {
        String driver = SettingUtil.get("example/example").get("demo", "key");
        assertEquals("value", driver);
    }

    @Test
    @DisplayName("获取第一个存在的 Setting")
    public void getFirstFoundTest() {
        //noinspection ConstantConditions
        String driver = SettingUtil.getFirstFound("test2", "test")
                .get("demo", "driver");
        assertEquals("com.mysql.jdbc.Driver", driver);
    }
}
