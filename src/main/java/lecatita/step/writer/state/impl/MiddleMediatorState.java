package lecatita.step.writer.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.enumeration.HeaderEnum;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class MiddleMediatorState implements IState {
	private static MiddleMediatorState instance = new MiddleMediatorState();

	private MiddleMediatorState() {
	}

	public static MiddleMediatorState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			if (context.isCorrectedSort()) {
				context.setNextState(HeaderRowState.instance());
			} else {
				context.setNextState(HeaderCollumState.instance());
			}

			context.update();
		} else {
			throw new IOException();
		}

	}

}