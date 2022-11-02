package lecatita.step.writer.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.step.writer.state.EquityContext;

public class HeaderCollumState implements IState {
	private static HeaderCollumState instance = new HeaderCollumState();

	private HeaderCollumState() {
	}

	public static HeaderCollumState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			if (context.isCorrectedSort()) {
				// insere as coluna
			} else {
				// insere as linhas
			}
			
			context.setNextState(HeaderRowState.instance());
			context.update();
		} else {
			throw new IOException();
		}

	}

}