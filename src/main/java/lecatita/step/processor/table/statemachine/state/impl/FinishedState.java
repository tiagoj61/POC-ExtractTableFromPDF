package lecatita.step.processor.table.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.line.statemachine.state.State;
import lecatita.step.processor.statemachine.StateTable;
import lecatita.step.processor.table.statemachine.context.ContextTable;

public class FinishedState implements StateTable {
	private static FinishedState instance = new FinishedState();

	private FinishedState() {
	}

	public static FinishedState instance() {
		return instance;
	}

	

	@Override
	public void updateState(ContextTable ctx) {
		System.out.println("Sucesso");
	}
}