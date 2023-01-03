package lecatita.step.processor.line.statemachine.state.impl;

import java.util.List;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.line.statemachine.state.State;

public class Q2 implements State {
	private static Q2 instance = new Q2();

	private Q2() {
	}

	public static Q2 instance() {
		return instance;
	}

	@Override
	public void updateState(LineContext ctx) {
		ctx.setNextState(Q0.instance());
		try {
			List<String> currentCollum = ctx.getColuns().get(ctx.getColuns().size() - 1);

			for (String colum : currentCollum) {
				if (colum.contains("%")) {

				} else {
					Double.valueOf(colum);
				}
			}

		} catch (Exception e) {
			ctx.setNextState(ErroState.instance(e));
		}
		ctx.update();
	}
}