
package lecatita.step.processor.statemachine.state;

import lecatita.step.processor.statemachine.context.DeliveryContext;

public interface State {
	public void updateState(DeliveryContext ctx);
}