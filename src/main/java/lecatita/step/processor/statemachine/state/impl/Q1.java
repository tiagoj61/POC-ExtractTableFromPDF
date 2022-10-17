package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class Q1 implements State {
	private static Q1 instance = new Q1();

	private Q1() {
	}

	public static Q1 instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		ctx.setNextState(Q2.instance());
		try {
			List<String> coluns = new LinkedList<String>(Arrays.asList(ctx.getCurrentLine().split(" ")));
			String header = (coluns.get(0));

			coluns.remove(0);
			//TODO: setar linah sem header
			ctx.addCollum(coluns);
			ctx.addIndeces(header);

		} catch (Exception e) {
			ctx.setNextState(ErroState.instance(e));
		}

		ctx.update();
	}
}