package lecatita.step.processor.table.statemachine.state.impl;

import lecatita.enumeration.PattenEnum;
import lecatita.enumeration.PatternReplaceEnum;
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

		String paginaString = ctx.getStringExtraida();
		String dealedPage = dealPage(paginaString);

		ctx.setStringTratada(dealedPage);
		ctx.update();
	}

	private String dealPage(String paginaString) {
		String dealed;

		dealed = PattenEnum.PIPE.getPattern().matcher(paginaString)
				.replaceAll(PatternReplaceEnum.PIPE_SPACE.getReplace());
		dealed = PattenEnum.LINEBREAK.getPattern().matcher(paginaString)
				.replaceAll(PatternReplaceEnum.LINEBREAK_PIPE.getReplace());
		dealed = PattenEnum.ENDFILE.getPattern().matcher(paginaString)
				.replaceAll(PatternReplaceEnum.ENDFILE_PIPE.getReplace());
		dealed = PattenEnum.SPACEPIPE.getPattern().matcher(paginaString)
				.replaceAll(PatternReplaceEnum.SPACEPIPE_PIPE.getReplace());
		dealed = PattenEnum.MULTIPLESPACE.getPattern().matcher(paginaString)
				.replaceAll(PatternReplaceEnum.MULTIPLESPACE_ONESPACE.getReplace());

		dealed = dealed.trim();
		return dealed;
	}
}