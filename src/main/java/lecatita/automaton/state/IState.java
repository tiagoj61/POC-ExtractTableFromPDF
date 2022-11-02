
package lecatita.automaton.state;

import lecatita.automaton.Context;

public interface IState {
	public void updateState(Context ctx) throws Exception;
}