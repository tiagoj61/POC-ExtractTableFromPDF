package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class EmptyTableState implements State {
	private static EmptyTableState instance = new EmptyTableState();

	private EmptyTableState() {
	}

	public static EmptyTableState instance(Exception e) {
		e.printStackTrace();
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		System.out.println("Empty table");
	}
}