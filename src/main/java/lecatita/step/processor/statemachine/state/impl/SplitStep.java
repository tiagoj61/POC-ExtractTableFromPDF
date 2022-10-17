package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class SplitStep implements State {
	private static SplitStep instance = new SplitStep();
	
	private String lineDelimeter = "\\|\\|";
	
	private SplitStep() {
	}

	public static SplitStep instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		System.out.println("Split Step");
		ctx.setNextState(Q0.instance());
		try {
			ctx.setLinesSplited(Arrays.asList(ctx.getTable().split(lineDelimeter)));
		} catch (Exception e) {
			ctx.setNextState(EmptyTableState.instance(e));
		}
		ctx.update();
	}
}