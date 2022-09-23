package lecatita.step.processor.statemachine.state.impl;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class Q0 implements State {
	private static Q0 instance = new Q0();

	private Q0() {
	}

	public static Q0 instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		ctx.setNextState(Q1.instance());
		try {
			String currentLine = ctx.getLines().get(ctx.getNumCurrentLine() + 1);
			ctx.setCurrentLine(currentLine);
		} catch (ArrayIndexOutOfBoundsException e) {
			ctx.setNextState(FinishedState.instance());
		}
		ctx.update();
	}
}