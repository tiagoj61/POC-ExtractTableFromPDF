package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

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
			System.out.println( ctx.getLines());
			String currentLine = ctx.getLines().get(ctx.getNumCurrentLine() + 1);
			ctx.setLine(currentLine);

			System.out.println(currentLine);
		} catch (ArrayIndexOutOfBoundsException e) {
			ctx.setNextState(FinishedState.instance());
		}

		ctx.update();
	}
}