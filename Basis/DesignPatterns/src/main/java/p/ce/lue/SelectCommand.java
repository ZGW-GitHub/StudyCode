package p.ce.lue;

/**
 * @author 愆凡
 * @date 2021/3/12 15:20
 */
public class SelectCommand implements ICommand {

	@Override
	public void commit(String str) {
		System.err.println("SelectCommand Commit");
	}

	@Override
	public void state() {
		System.err.println("SelectCommand State");
	}

}
