package o.mo.ban;

/**
 * @author 愆凡
 * @date 2021/3/12 17:45
 */
public abstract class Command {

	/**
	 * 提交命令
	 */
	abstract void commit();

	/**
	 * 查询命令执行状态
	 */
	abstract void state();

	/**
	 * 执行命令 - 模板方法
	 */
	final void executeCommand() {
		commit();
		state();
	}

}
