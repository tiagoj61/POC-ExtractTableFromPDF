package lecatita.step.processor.line.statemachine.state.impl;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.line.statemachine.state.State;

public class CleanLineState implements State {
	private static CleanLineState instance = new CleanLineState();

	private CleanLineState() {
	}

	public static CleanLineState instance() {
		return instance;
	}

	@Override
	public void updateState(LineContext ctx) {
		ctx.setNextState(Q1.instance());
		
		String currentLine=ctx.getCurrentLine();
		currentLine=currentLine.trim();
		ctx.setCurrentLine(currentLine);		
		
		ctx.update();
	}
}