package lecatita.step.writer.state.impl;

import java.io.IOException;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.enumeration.HeaderEnum;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class StartMediatorState implements IState {
	private static StartMediatorState instance = new StartMediatorState();

	private StartMediatorState() {
	}

	public static StartMediatorState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			LineContext lineContext = context.getLineContext();
			if (HeaderEnum.contains(lineContext.getHeader().split(" "))) {
				context.setCorrectedSort(true);
			} else {
				context.setCorrectedSort(false);
			}

			context.setNextState(HeaderCollumState.instance());
			context.update();
		} else {
			throw new IOException();
		}

	}

}