package lecatita.step.processor.table.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import lecatita.automaton.StateTable;
import lecatita.step.processor.table.statemachine.context.ContextTable;

public class TableExtractorState implements StateTable {
	private static TableExtractorState instance = new TableExtractorState();

	private TableExtractorState() {
	}

	public static TableExtractorState instance() {
		return instance;
	}
	// TODO arrumar esse troço
	@Override
	public void updateState(ContextTable ctx) {
		ctx.setNextState(FinishedState.instance());
		List<String> linhas = new ArrayList<>(Arrays.asList(ctx.getStringTratada().split("\\|")));
		Map<Integer, List<String>> ocorrencias = new HashMap<Integer, List<String>>();
		List<String> grupo = new ArrayList<>();
		List<List<String>> tabelas = new ArrayList<>();
		int ant = -1;
		String ants = "";
		
		for (int i = 0; i < linhas.size(); i++) {

			String[] arra = linhas.get(i).split(" ");
			int qtdNum = 0;
			for (int j = 0; j < arra.length; j++) {
				if (verifyContainsNumber(arra[j])) {
					qtdNum++;
				}
			}
			// TODO: tem que ser seguido as linhas com mesma qtd

			if (ocorrencias.get(qtdNum) != null) {
				if (ant == qtdNum) {
					ocorrencias.get(qtdNum).add(linhas.get(i));
				}
			} else {
				for (Map.Entry<Integer, List<String>> entry : ocorrencias.entrySet()) {
					if (entry.getKey() > 1 && entry.getValue().size() > 1) {
						List<String> tabelanova = new ArrayList<>();
						tabelanova.add(ants);
						for (String a : entry.getValue()) {
							tabelanova.add(a);
						}
						tabelas.add(tabelanova);
						ocorrencias = new HashMap<Integer, List<String>>();
						break;
					}
				}
				grupo = new ArrayList<>();
				grupo.add(linhas.get(i));
				ocorrencias.put(qtdNum, grupo);
			}
			if (i == linhas.size() - 1) {
				for (Map.Entry<Integer, List<String>> entry : ocorrencias.entrySet()) {
					if (entry.getKey() > 1 && entry.getValue().size() > 1) {
						List<String> tabelanova = new ArrayList<>();
						tabelanova.add(ants);
						for (String a : entry.getValue()) {
							tabelanova.add(a);
						}
						tabelas.add(tabelanova);
						ocorrencias = new HashMap<Integer, List<String>>();
						break;
					}
				}
			}

			if (ant != qtdNum && qtdNum > 2 && i > 0) {
				ants = linhas.get(i - 1);
			}
			ant = qtdNum;
		}
		String[] tabelasRetorno = new String[tabelas.size()];
		for (int j = 0; j < tabelasRetorno.length; j++) {
			tabelasRetorno[j] = String.join("|", tabelas.get(j));
		}
		ctx.setTabelasExtraidas(tabelasRetorno);
		ctx.update();
	}

	// TODO TA DUPLICADO
	private boolean verifyContainsNumber(String atual) {
		if (atual.contains("%"))
			return true;
		String regex = "(.)*(\\d)(.)*";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(atual).matches();
	}
}