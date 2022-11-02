package lecatita.step.processor.line.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.line.statemachine.state.State;

public class FinishedState implements State {
	private static FinishedState instance = new FinishedState();

	private FinishedState() {
	}

	public static FinishedState instance() {
		return instance;
	}

	@Override
	public void updateState(LineContext ctx) {
		System.out.println("Sucesso");
	}
}