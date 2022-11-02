package lecatita.automaton;

import lecatita.automaton.state.IState;

public class Context {
	private IState nextState;
	private String packageId;

	public Context(String packageId) {
		this.packageId = packageId;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public IState getNextState() {
		return nextState;
	}

	public void setNextState(IState nextState) {
		this.nextState = nextState;
	}

	public void update() {
		try {
			nextState.updateState(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}