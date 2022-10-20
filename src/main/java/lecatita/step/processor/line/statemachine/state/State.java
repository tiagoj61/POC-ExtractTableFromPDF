
package lecatita.step.processor.line.statemachine.state;

import lecatita.step.processor.line.statemachine.context.ContextLine;

public interface State {
	public void updateState(ContextLine ctx);
}