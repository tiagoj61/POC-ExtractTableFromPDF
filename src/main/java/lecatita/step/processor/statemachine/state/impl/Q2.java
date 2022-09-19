package lecatita.step.processor.statemachine.state.impl;

import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class Q2 implements State {
	private static Q2 instance = new Q2();

	private Q2() {
	}

	public static Q2 instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		ctx.setNextState(FinishedState.instance());
		try {
			List<String> coluns = ctx.getColuns().get(ctx.getColuns().size());

			String delimiter = coluns.get(coluns.size());
			coluns.remove(coluns.size());
			System.out.println(coluns.get(0));
			for (String colum : coluns) {

				Double.valueOf(colum);

			}

			if (!delimiter.equals("||")) {
				ctx.setNextState(ErroState.instance(new Exception()));
			}

		} catch (Exception e) {
			ctx.setNextState(ErroState.instance(e));
		}
		ctx.update();
	}
}