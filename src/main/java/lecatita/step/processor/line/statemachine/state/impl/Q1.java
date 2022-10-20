package lecatita.step.processor.line.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.line.statemachine.state.State;

public class Q1 implements State {
	private static Q1 instance = new Q1();

	private Q1() {
	}

	public static Q1 instance() {
		return instance;
	}

	@Override
	public void updateState(ContextLine ctx) {
		ctx.setNextState(Q2.instance());
		try {
			List<String> coluns = new ArrayList<>(Arrays.asList(ctx.getCurrentLine().split(" ")));
			coluns = groupIndices(coluns);
			String indice = (coluns.get(0));

			coluns.remove(0);
			// TODO: verificador de linhas tem indice
			ctx.addCollum(coluns);
			ctx.addIndeces(indice);

		} catch (Exception e) {
			ctx.setNextState(ErroState.instance(e));
		}

		ctx.update();
	}
	//Se não tiver numero na strign é um indice
	private List<String> groupIndices(List<String> tudo) {
		String indice = tudo.get(0);
		String space = " ";
		List<String> listaAgrupada = new ArrayList<String>();
		int i;
		for (i = 1; i < tudo.size(); i++) {
			if (!verifyContainsNumber(tudo.get(i))) {
				indice = indice + space + tudo.get(i);
			} else {
				break;
			}
		}
		listaAgrupada.add(indice);
		for (i = i; i < tudo.size(); i++) {
			listaAgrupada.add(tudo.get(i));
		}
		return listaAgrupada;
	}

	private boolean verifyContainsNumber(String atual) {
		String regex = "(.)*(\\d)(.)*";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(atual).matches();
	}
}