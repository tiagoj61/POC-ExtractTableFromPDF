package lecatita.step.processor.table.statemachine.state.impl;

import lecatita.enumeration.PatternCaractersEnum;
import lecatita.enumeration.CaractersEnum;
import lecatita.step.processor.statemachine.StateTable;
import lecatita.step.processor.table.statemachine.context.ContextTable;

public class TableClennerState implements StateTable {
	private static TableClennerState instance = new TableClennerState();

	public TableClennerState() {
	}

	public static TableClennerState instance() {
		return instance;
	}

	@Override
	public void updateState(ContextTable ctx) {
		ctx.setNextState(TableExtractorState.instance());

		String paginaString = ctx.getStringExtraida();
		String dealedPage = dealPage(paginaString);

		ctx.setStringTratada(dealedPage);
		ctx.update();
	}

	private String dealPage(String paginaString) {
		String dealed;

		dealed = PatternCaractersEnum.PIPE.getPattern().matcher(paginaString)
				.replaceAll(CaractersEnum.PIPE_SPACE.getReplace());
		dealed = PatternCaractersEnum.LINEBREAK.getPattern().matcher(paginaString)
				.replaceAll(CaractersEnum.LINEBREAK_PIPE.getReplace());
		dealed = PatternCaractersEnum.ENDFILE.getPattern().matcher(paginaString)
				.replaceAll(CaractersEnum.ENDFILE_PIPE.getReplace());
		dealed = PatternCaractersEnum.SPACEPIPE.getPattern().matcher(paginaString)
				.replaceAll(CaractersEnum.SPACEPIPE_PIPE.getReplace());
		dealed = PatternCaractersEnum.MULTIPLESPACE.getPattern().matcher(paginaString)
				.replaceAll(CaractersEnum.MULTIPLESPACE_ONESPACE.getReplace());

		dealed = dealed.trim();
		return dealed;
	}
}