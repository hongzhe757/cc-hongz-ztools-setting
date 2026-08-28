package cc.hongz.ztools.setting;

import cc.hongz.ztools.core.util.ArrayUtil;
import cc.hongz.ztools.setting.dialect.Props;
import cc.hongz.ztools.setting.dialect.PropsUtil;
import lombok.Data;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Issue3008 数组字段赋值")
public class Issue3008Test {

	/**
	 * 数组字段追加后生成新的数组，造成赋值丢失<br>
	 * 修复见：BeanUtil.setFieldValue
	 */
	@Test
	@DisplayName("数组字段追加赋值")
	public void toBeanTest() {
		final Props props = PropsUtil.get("issue3008");
		final MyUser user = props.toBean(MyUser.class, "person");
		assertEquals("[LOL, KFC, COFFE]", ArrayUtil.toString(user.getHobby()));
	}

	@Data
	static class MyUser {
		private String[] hobby;
	}
}
