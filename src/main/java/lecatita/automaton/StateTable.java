
package lecatita.automaton;

import lecatita.step.processor.table.statemachine.context.ContextTable;

public interface StateTable {
	public void updateState(ContextTable ctx);
}