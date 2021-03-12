package o.mo.ban;

import org.junit.Test;

/**
 * @author 愆凡
 * @date 2021/3/12 17:51
 */
public class DemoTest {

	@Test
	public void test() {
		Command select = new SelectCommand();
		select.executeCommand();

		Command delect = new DelectCommand();
		delect.executeCommand();
	}

}
