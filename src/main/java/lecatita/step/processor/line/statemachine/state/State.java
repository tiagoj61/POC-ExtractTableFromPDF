
package lecatita.step.processor.line.statemachine.state;

import lecatita.step.processor.line.statemachine.context.LineContext;

public interface State {
	public void updateState(LineContext ctx);
}