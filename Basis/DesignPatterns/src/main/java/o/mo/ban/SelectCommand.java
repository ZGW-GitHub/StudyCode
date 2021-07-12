package o.mo.ban;

/**
 * @author 愆凡
 * @date 2021/3/12 17:49
 */
public class SelectCommand extends Command {

	@Override
	void commit() {
		System.err.println("Commit Select");
	}

	@Override
	void state() {
		System.err.println("Select State");
	}

}
