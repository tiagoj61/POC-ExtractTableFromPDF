package lecatita.step.writer.state;

import lecatita.automaton.Context;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.impl.StartMediatorState;

public class EquityContext extends Context {

	private LineContext lineContext;
	private Boolean correctedSort;

	public EquityContext(String packageId, LineContext lineContext) {
		super(packageId);
		this.lineContext = lineContext;
		this.setNextState(StartMediatorState.instance());
	}

	public LineContext getLineContext() {
		return lineContext;
	}

	public void setLineContext(LineContext lineContext) {
		this.lineContext = lineContext;
	}

	public Boolean isCorrectedSort() {
		return correctedSort;
	}

	public void setCorrectedSort(Boolean correctedSort) {
		this.correctedSort = correctedSort;
	}

}