package lecatita.step.processor.line.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.line.statemachine.state.State;

public class EmptyTableState implements State {
	private static EmptyTableState instance = new EmptyTableState();

	private EmptyTableState() {
	}

	public static EmptyTableState instance(Exception e) {
		e.printStackTrace();
		return instance;
	}

	@Override
	public void updateState(LineContext ctx) {
		System.out.println("Empty table");
	}
}