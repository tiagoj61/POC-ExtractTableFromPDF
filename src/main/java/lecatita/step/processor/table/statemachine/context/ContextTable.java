package lecatita.step.processor.table.statemachine.context;

import lecatita.step.processor.line.statemachine.state.State;
import lecatita.step.processor.table.statemachine.state.StateTable;
import lecatita.step.processor.table.statemachine.state.impl.TableClennerState;
import lecatita.step.processor.table.statemachine.state.impl.TableExtractorState;

public class ContextTable {
	private StateTable nextState;
	private String packageId;


	public ContextTable(String packageId, String table) {
		nextState=TableClennerState.instance();
	}
	public StateTable getNextState() {
		return nextState;
	}

	public void setNextState(StateTable nextState) {
		this.nextState = nextState;
	}
	public void update() {
		nextState.updateState(this);
	}
}