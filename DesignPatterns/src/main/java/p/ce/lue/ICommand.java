package p.ce.lue;

/**
 * 用来约束、标识策略
 *
 * @author 愆凡
 * @date 2021/3/12 15:18
 */
public interface ICommand {

	/**
	 * 提交命令
	 *
	 * @param str str
	 */
	void commit(String str);

	/**
	 * 获取命令执行状态
	 */
	void state();

	/**
	 * 获取具体策略
	 *
	 * @param str str
	 * @return {@link ICommand}
	 */
	@SuppressWarnings("all")
	static ICommand getInstance(String str) {
		if ("select".equals(str)) {
			return new SelectCommand();
		}
		if ("delete".equals(str)) {
			return new DeleteCommand();
		}

		throw new RuntimeException("该命令没有对应的策略来处理");
	}

}
