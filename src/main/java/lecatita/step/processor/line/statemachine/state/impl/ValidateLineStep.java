package lecatita.step.processor.line.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.line.statemachine.state.State;

public class ValidateLineStep implements State {
	private static ValidateLineStep instance = new ValidateLineStep();
	
	private String lineDelimeter = "\\|";
	
	private ValidateLineStep() {
	}

	public static ValidateLineStep instance() {
		return instance;
	}

	@Override
	public void updateState(LineContext ctx) {
		ctx.setNextState(Q0.instance());
		try {
			List<String> linhas = new ArrayList<>(Arrays.asList(ctx.getTable().split(lineDelimeter)));
			
			ctx.setHeader(linhas.get(0));
			linhas.remove(0);
			
			ctx.setLinesSplited(linhas);
		} catch (Exception e) {
			ctx.setNextState(EmptyTableState.instance(e));
		}
		ctx.update();
	}
}