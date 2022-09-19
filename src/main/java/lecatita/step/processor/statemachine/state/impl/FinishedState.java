package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class FinishedState implements State {
	private static FinishedState instance = new FinishedState();

	private FinishedState() {
	}

	public static FinishedState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		System.out.println("Sucesso");
	}
}