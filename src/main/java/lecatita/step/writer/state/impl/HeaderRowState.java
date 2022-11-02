package lecatita.step.writer.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.enumeration.HeaderEnum;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class HeaderRowState implements IState {
	private static HeaderRowState instance = new HeaderRowState();

	private HeaderRowState() {
	}

	public static HeaderRowState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			if (context.isCorrectedSort()) {
				// insere as linha
			}else {
				// insere as coluna
			}
			context.setNextState(CollumState.instance());
			context.update();
		} else {
			throw new IOException();
		}

	}

}