package lecatita.step.processor.table.statemachine.context;

import lecatita.step.processor.line.statemachine.state.State;
import lecatita.step.processor.statemachine.StateTable;
import lecatita.step.processor.table.statemachine.state.impl.TableClennerState;
import lecatita.step.processor.table.statemachine.state.impl.TableExtractorState;

public class ContextTable {
	private StateTable nextState;
	private String packageId;
	private String stringExtraida;
	private String stringTratada;
	private String[] tabelasExtraidas;

	public ContextTable(String packageId, String table) {
		stringExtraida= table;
		nextState = TableClennerState.instance();
	}

	public StateTable getNextState() {
		return nextState;
	}

	public void setNextState(StateTable nextState) {
		this.nextState = nextState;
	}

	public void update() {
		nextState.updateState(this);
	}

	public String getPackageId() {
		return packageId;
	}

	public void setPackageId(String packageId) {
		this.packageId = packageId;
	}

	

	public String getStringExtraida() {
		return stringExtraida;
	}

	public void setStringExtraida(String stringExtraida) {
		this.stringExtraida = stringExtraida;
	}

	public String getStringTratada() {
		return stringTratada;
	}

	public void setStringTratada(String stringTratada) {
		this.stringTratada = stringTratada;
	}

	public String[] getTabelasExtraidas() {
		return tabelasExtraidas;
	}

	public void setTabelasExtraidas(String[] tabelasExtraidas) {
		this.tabelasExtraidas = tabelasExtraidas;
	}
	
	
}