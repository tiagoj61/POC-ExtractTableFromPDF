package lecatita.step.processor.line.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.line.statemachine.state.State;

public class ErroState implements State {
	private static ErroState instance = new ErroState();

	private ErroState() {
	}

	public static ErroState instance(Exception e) {
		e.printStackTrace();
		return instance;
	}

	@Override
	public void updateState(ContextLine ctx) {
		System.out.println("Deu erro");
	}
}