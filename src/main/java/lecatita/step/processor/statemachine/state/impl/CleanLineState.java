package lecatita.step.processor.statemachine.state.impl;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.context.Context;
import lecatita.step.processor.statemachine.state.State;

public class CleanLineState implements State {
	private static CleanLineState instance = new CleanLineState();

	private CleanLineState() {
	}

	public static CleanLineState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) {
		ctx.setNextState(Q1.instance());
		String currentLine=ctx.getCurrentLine();
		currentLine=currentLine.trim();
		ctx.setCurrentLine(currentLine);		
		ctx.update();
	}
}