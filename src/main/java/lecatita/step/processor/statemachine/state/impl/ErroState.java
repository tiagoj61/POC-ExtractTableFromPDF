package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class ErroState implements State {
	private static ErroState instance = new ErroState();

	private ErroState() {
	}

	public static ErroState instance(Exception e) {
		e.printStackTrace();
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		System.out.println("Deu erro");
	}
}