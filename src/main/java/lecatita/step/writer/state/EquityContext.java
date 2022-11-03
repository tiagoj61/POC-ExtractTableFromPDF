package lecatita.step.writer.state;

import lecatita.automaton.Context;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.impl.StartMediatorState;

public class EquityContext extends Context {

	private LineContext lineContext;
	private Boolean correctedSort;
	private int[] indecesMenWomen;

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

	public void setCorrectedSort(Boolean correctedSort) {
		this.correctedSort = correctedSort;
	}

	public int[] getIndecesMenWomen() {
		return indecesMenWomen;
	}

	public void setIndecesMenWomen(int[] indecesMenWomen) {
		this.indecesMenWomen = indecesMenWomen;
	}

	public Boolean getCorrectedSort() {
		return correctedSort;
	}

}