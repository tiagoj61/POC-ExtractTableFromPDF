
package lecatita.step.processor.statemachine.state;

import lecatita.step.processor.statemachine.context.Context;

public interface State {
	public void updateState(Context ctx);
}