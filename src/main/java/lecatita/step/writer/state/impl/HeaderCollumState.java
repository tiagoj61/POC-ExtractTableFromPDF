package lecatita.step.writer.state.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import lecatita.automaton.Context;
import lecatita.automaton.state.IState;
import lecatita.enumeration.HeaderEnum;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class HeaderCollumState implements IState {
	private static HeaderCollumState instance = new HeaderCollumState();

	private HeaderCollumState() {
	}

	public static HeaderCollumState instance() {
		return instance;
	}

	@Override
	public void updateState(Context ctx) throws IOException {
		if (ctx instanceof EquityContext) {
			EquityContext context = (EquityContext) ctx;

			LineContext lineContext = context.getLineContext();

			String[] headers;
			if (context.getCorrectedSort()) {
				headers = lineContext.getHeader().split(" ");
			} else {
				headers = lineContext.getIndeces().toArray(new String[0]);
			}

			int[] indecesHeader = getIndeces(headers);
			context.setIndecesMenWomen(indecesHeader);

			context.setNextState(HeaderRowState.instance());
			context.update();
		} else {
			throw new IOException();
		}
	}

	public int[] getIndeces(String[] headers) {
	
		int[] indeces = new int[2];
		try {
		List<Integer> indexOfMens = HeaderEnum.positionOfMenEquals(headers);
		List<Integer> indexOfWomens = HeaderEnum.positionOfWomenEquals(headers);
		indexOfWomens = indexOfWomens.stream().filter(women -> indexOfMens.indexOf(women) == -1)
				.collect(Collectors.toList());
		indeces[0] = indexOfMens.get(indexOfMens.size() - 1);
		indeces[1] = indexOfWomens.get(indexOfWomens.size() - 1);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return indeces;
	}

}