package lecatita.step.processor.table.statemachine.state.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import lecatita.step.processor.table.statemachine.context.ContextTable;
import lecatita.step.processor.table.statemachine.state.StateTable;

public class TableExtractorState implements StateTable {
	private static TableExtractorState instance = new TableExtractorState();

	private int posCurrentLine = -1;
	private String a = "405-1 Diversidade em órgãos de governança e empregados|\n"
			+ "Composição do Conselho de Administração - Gênero 2020 2021|\r\n" + "Masculinos 11 92% 11 92|\r\n"
			+ "Femininos 1 8% 1 8%|\r\n" + "Faixa Etária Gênero|\r\n" + "Cargos|\r\n"
			+ "-30 anos 30 a 50 anos +50 anos Homens Mulheres|\r\n" + "Presidente/Diretor 0% 94% 6% 92% 8%|\r\n"
			+ "Superintendente/VP 4% 90% 6% 83% 17%|\r\n" + "Gerente/Gerente Geral 6% 93% 1% 59% 41%|";

	private TableExtractorState() {
	}

	public static TableExtractorState instance() {
		return instance;
	}

	@Override
	public void updateState(ContextTable ctx) {
		List<String> linhas = new ArrayList<>(Arrays.asList(a.split("\\|")));
		Map<Integer, List<String>> ocorrencias = new HashMap<Integer, List<String>>();
		List<String> grupo = new ArrayList<>();
		List<List<String>> tabelas = new ArrayList<>();
		int ant = -1;
		String ants = "";
		// TODO Criar metodo, conta a quantidade de numeros agrupados na linha
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
					if(entry.getKey()>1 && entry.getValue().size()>1) {
						 List<String> tabelanova = new ArrayList<>();
						 tabelanova.add(ants);
						 for (String a: entry.getValue()) {
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
			if(i==linhas.size()-1) {
				for (Map.Entry<Integer, List<String>> entry : ocorrencias.entrySet()) {
					if(entry.getKey()>1 && entry.getValue().size()>1) {
						 List<String> tabelanova = new ArrayList<>();
						 tabelanova.add(ants);
						 for (String a: entry.getValue()) {
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

	

		// ctx.update();
	}


	// TODO TA DUPLICADO
	private boolean verifyContainsNumber(String atual) {
		if(atual.contains("%"))
			return true;
		String regex = "(.)*(\\d)(.)*";
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(atual).matches();
	}
}