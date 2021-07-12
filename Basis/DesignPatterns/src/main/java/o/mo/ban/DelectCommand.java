package o.mo.ban;

/**
 * @author 愆凡
 * @date 2021/3/12 17:50
 */
public class DelectCommand extends Command {

	@Override
	void commit() {
		System.err.println("Commit Delect");
	}

	@Override
	void state() {
		System.err.println("Delect State");
	}

}
