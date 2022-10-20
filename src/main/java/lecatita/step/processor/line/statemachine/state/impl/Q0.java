package lecatita.step.processor.line.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.line.statemachine.state.State;

public class Q0 implements State {
	private static Q0 instance = new Q0();

	private int posCurrentLine = -1;

	private Q0() {
	}

	public static Q0 instance() {
		return instance;
	}

	@Override
	public void updateState(ContextLine ctx) {
		ctx.setNextState(CleanLineState.instance());
		try {
			String currentLine = ctx.getLinesSplited().get(this.posCurrentLine++);
			ctx.setCurrentLine(currentLine);
		} catch (Exception e) {
			ctx.setNextState(FinishedState.instance());
		}
		ctx.update();
	}
}