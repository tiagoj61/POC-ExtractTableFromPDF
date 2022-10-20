
package lecatita.step.processor.table.statemachine.state;

import lecatita.step.processor.table.statemachine.context.ContextTable;

public interface StateTable {
	public void updateState(ContextTable ctx);
}