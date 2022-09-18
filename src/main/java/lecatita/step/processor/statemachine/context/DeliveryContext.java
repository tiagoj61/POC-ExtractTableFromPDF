package lecatita.step.processor.statemachine.context;

import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.state.State;
import lecatita.step.processor.statemachine.state.impl.Q0;
import lecatita.step.processor.statemachine.state.impl.Q1;

public class DeliveryContext {

	private State nextState;
	private String packageId;
	private int numCurrentLine;
	private String line;
	private List<String> lines;
	private List<List<String>> coluns;
	private String header;

	public DeliveryContext(String packageId, String line) {
		super();
		this.line = line;
		this.lines = Arrays.asList(line.split("||"));
		this.numCurrentLine = -1;
		this.packageId = packageId;

		if (this.nextState == null) {
			this.nextState = Q0.instance();
		}
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public int getNumCurrentLine() {
		return numCurrentLine;
	}

	public void setNumCurrentLine(int numCurrentLine) {
		this.numCurrentLine = numCurrentLine;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	public List<List<String>> getColuns() {
		return coluns;
	}

	public void setColuns(List<List<String>> coluns) {
		this.coluns = coluns;
	}

	public List<String> getLines() {
		return lines;
	}

	public void setLines(List<String> lines) {
		this.lines = lines;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public void update() {
		nextState.updateState(this);
	}
}