package lecatita.step.writer.state.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.automaton.state.impl.FinishState;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;
import lecatita.step.writer.state.model.Burden;

public class HeaderRowState implements IState {
	private static HeaderRowState instance = new HeaderRowState();

	private HeaderRowState() {
	}

	public static HeaderRowState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			LineContext lineContext = context.getLineContext();
			List<String> burdens;
			List<Burden> burdensData = new ArrayList();
			List<List<String>> values = lineContext.getColuns();
			int[] indecesHeader = context.getIndecesMenWomen();
			if (context.getCorrectedSort()) {
				burdens = lineContext.getIndeces();
			} else {
				burdens = Arrays.asList(lineContext.getHeader().split(" "));
			}
			// adm opp asd
			// burdens.stream().map(burden->new Burden(burden, 0, 0))

			IntStream.range(0, burdens.size()).forEach(index -> {
				burdensData.add(new Burden(burdens.get(index), values.get(index).get(indecesHeader[0]),
						values.get(index).get(indecesHeader[1])));
			});
			context.setBurdensData(burdensData);
			context.setNextState(FinishState.instance());
			context.update();
		} else {
			throw new IOException();
		}

	}

}