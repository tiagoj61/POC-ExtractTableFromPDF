package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
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

			List<String> coluns = Arrays.asList(ctx.getLine().split(" "));
			ctx.setHeader(coluns.get(0));
			System.out.println(coluns);
			System.out.println("colun");
			for (String colum : coluns) {

				System.out.println(colum);

			}

			coluns.remove(0);
			List<List<String>> onlyColluns = ctx.getColuns();
			onlyColluns.add(coluns);
			ctx.setColuns(onlyColluns);
			System.out.println(coluns.get(0));

		} catch (Exception e) {
			ctx.setNextState(ErroState.instance(e));
		}

		ctx.update();
	}
}