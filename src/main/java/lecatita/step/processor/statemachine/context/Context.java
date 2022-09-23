package lecatita.step.processor.statemachine.context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lecatita.step.processor.statemachine.state.State;
import lecatita.step.processor.statemachine.state.impl.Q0;
import lecatita.step.processor.statemachine.state.impl.Q1;

public class Context {
	private String lineDelimeter = "\\|\\|";
	private State nextState;
	private String packageId;
	private int numCurrentLine;

	private String table;
	private String currentLine;
	private List<String> lines;

	private List<String> headers;
	private List<List<String>> coluns;
	private String header;

	public Context(String packageId, String table) {
		this.packageId = packageId;

		this.table = table;
		this.lines = Arrays.asList(table.split(lineDelimeter));

		this.numCurrentLine = -1;

		this.nextState = Q0.instance();
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

	public String getLineDelimeter() {
		return lineDelimeter;
	}

	public void setLineDelimeter(String lineDelimeter) {
		this.lineDelimeter = lineDelimeter;
	}

	public List<String> getHeaders() {
		return headers;
	}

	public void setHeaders(List<String> headers) {
		this.headers = headers;
	}

	public void addHeader(String header) {
		if (this.headers == null) {
			this.headers = new ArrayList<String>();
		}
		this.headers.add(header);
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