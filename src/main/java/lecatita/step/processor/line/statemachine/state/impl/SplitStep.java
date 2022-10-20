package lecatita.step.processor.line.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.line.statemachine.state.State;

public class SplitStep implements State {
	private static SplitStep instance = new SplitStep();

	private String lineDelimeter = "\\|";
	private String lineDelimeterDuplo = "\\|\\|";

	private SplitStep() {
	}

	public static SplitStep instance() {
		return instance;
	}

	@Override
	public void updateState(ContextLine ctx) {
		System.out.println("Split Step");
		ctx.setNextState(Q0.instance());
		try {
			if (ctx.getTable().contains("||")) {
				lineDelimeter = lineDelimeterDuplo;
			}
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