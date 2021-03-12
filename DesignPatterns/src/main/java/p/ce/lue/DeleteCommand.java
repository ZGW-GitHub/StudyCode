package p.ce.lue;

/**
 * @author 愆凡
 * @date 2021/3/12 15:30
 */
public class DeleteCommand implements ICommand {

	@Override
	public void commit(String str) {
		System.err.println("DeleteCommand Commit");
	}

	@Override
	public void state() {
		System.err.println("DeleteCommand State");
	}

}
