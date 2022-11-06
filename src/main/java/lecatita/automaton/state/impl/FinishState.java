package lecatita.automaton.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.step.writer.state.EquityContext;

public class FinishState implements IState {
	private static FinishState instance = new FinishState();

	private FinishState() {
	}

	public static FinishState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {

	}

}