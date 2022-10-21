package lecatita.step.processor.table.statemachine.state.impl;

import lecatita.step.processor.statemachine.StateTable;
import lecatita.step.processor.table.statemachine.context.ContextTable;

public class TableClennerState implements StateTable {
	private static TableClennerState instance = new TableClennerState();

	private TableClennerState() {
	}

	public static TableClennerState instance() {
		return instance;
	}

	@Override
	public void updateState(ContextTable ctx) {
		ctx.setNextState(TableExtractorState.instance());
		ctx.setStringTratada(ctx.getStringExtraida().replaceAll("(\\|+)", " ").replaceAll("[\\n\\r]+", "\\|\n").replaceAll(" $", "\\|").replaceAll("( +)", " ")
				.replaceAll(" \\|", "\\|").trim());
		ctx.update();
	}
}