package p.ce.lue;

import org.junit.Test;

/**
 * @author 愆凡
 * @date 2021/3/12 15:32
 */
public class DemoTest {

	@Test
	public void test() {
		doTest("select");
		doTest("delete");
	}

	private void doTest(String param) {
		ICommand command = ICommand.getInstance(param);

		command.commit(param);
		command.state();
	}

}
