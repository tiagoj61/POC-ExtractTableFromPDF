package lecatita.step.writer.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.step.writer.state.EquityContext;

public class CollumState implements IState {
	private static CollumState instance = new CollumState();

	private CollumState() {
	}

	public static CollumState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			if (context.getCorrectedSort()) {
				// nome valor genero
				// diretor [0-diretor][1-masculino] masculino
			}

			context.update();
		} else {
			throw new IOException();
		}

	}

}