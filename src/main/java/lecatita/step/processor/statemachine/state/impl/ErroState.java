package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.DeliveryContext;
import lecatita.step.processor.statemachine.state.State;

public class ErroState implements State {
	private static ErroState instance = new ErroState();

	private ErroState() {
	}

	public static ErroState instance() {
		return instance;
	}

	@Override
	public void updateState(DeliveryContext ctx) {
		System.out.println("Deu erro");
	}
}