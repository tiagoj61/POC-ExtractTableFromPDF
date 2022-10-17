package lecatita.step.processor.statemachine.context;

import java.util.ArrayList;
import java.util.List;

import lecatita.step.processor.statemachine.state.State;
import lecatita.step.processor.statemachine.state.impl.SplitStep;

public class Context {
	private State nextState;
	private String packageId;

	private String table;
	private List<String> linesSplited;
	private String currentLine;
	
	private List<String> indeces;
	private List<List<String>> coluns;

	public Context(String packageId, String table) {
		this.packageId = packageId;

		this.table = table;
		
		this.nextState = SplitStep.instance();
	}

	public String getTable() {
		return table;
	}

	public void setTable(String table) {
		this.table = table;
	}

	public String getCurrentLine() {
		return currentLine;
	}


	public List<String> getIndeces() {
		return indeces;
	}

	public void setIndices(List<String> indeces) {
		this.indeces = indeces;
	}

	public void addIndeces(String indeces) {
		if (this.indeces == null) {
			this.indeces = new ArrayList<String>();
		}
		this.indeces.add(indeces);
	}

	public void setCurrentLine(String currentLine) {
		this.currentLine = currentLine;
	}

	public State getNextState() {
		return nextState;
	}

	public void setNextState(State nextState) {
		this.nextState = nextState;
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	public List<List<String>> getColuns() {
		return coluns;
	}

	public void setColuns(List<List<String>> coluns) {
		this.coluns = coluns;
	}

	public void addCollum(List<String> colum) {
		if (this.coluns == null) {
			this.coluns = new ArrayList<List<String>>();
		}
		this.coluns.add(colum);
	}

	


	public List<String> getLinesSplited() {
		return linesSplited;
	}

	public void setLinesSplited(List<String> linesSplited) {
		this.linesSplited = linesSplited;
	}

	public void update() {
		nextState.updateState(this);
	}
}