
package lecatita.step.processor.statemachine;

import lecatita.step.processor.table.statemachine.context.ContextTable;

public interface StateTable {
	public void updateState(ContextTable ctx);
}