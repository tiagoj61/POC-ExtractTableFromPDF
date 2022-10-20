package lecatita.step.processor.table.statemachine.state.impl;

import lecatita.step.processor.table.statemachine.context.ContextTable;
import lecatita.step.processor.table.statemachine.state.StateTable;

public class TableClennerState implements StateTable {
	private static TableClennerState instance = new TableClennerState();

	private int posCurrentLine = -1;
	private String a = "Composição do Conselho de Administração - Gênero 2020 2021|\r\n"
			+ "Masculinos 11 92% 11 92%| 4|\r\n"
			+ "Femininos 1 8% 1 8% 4|\r\n"
			+ "Composição do Conselho de Administração - Faixa Etária||2020||||2021|||\r\n"
			+ "Abaixo de 30 anos|0||0%||0|0%|||\r\n"
			+ "Entre 30 e 50 anos|9|75%|||10|83%|||\r\n"
			+ "Acima de 50 anos|3|25%|||2|17%|||\r\n"
			+ "Faixa Etária||||Gênero|||||\r\n"
			+ "Cargos|||||||||\r\n"
			+ "-30 anos 30 a 50 anos||+50 anos||Homens||Mulheres|||\r\n"
			+ "Presidente/Diretor 0% 94%||6%||92%||8%|||\r\n"
			+ "Superintendente/VP 4% 90%||6%||83%||17%|||\r\n"
			+ "Gerente/Gerente Geral 6% 93%||1%||59%||41%|||\r\n"
			+ "Coordenador/Consultor 22% 77%||1%||67%||33%|||\r\n"
			+ "Técnico/Analista/Supervisor 44% 55%||1%||69%||31%|||\r\n"
			+ "Operacional 64% 32%||4%||0%||0%|||\r\n"
			+ "Estagiário 99% 1%||0%||72%||28%|||\r\n"
			+ "Aprendiz 100% 0%||0%||60%||40%|||\r\n"
			+ "Total Colaboradores 40% 58%||1%||66%||34%|||\r\n"
			+ "||||||||108|";

	private TableClennerState() {
	}

	public static TableClennerState instance() {
		return instance;
	}

	@Override
	public void updateState(ContextTable ctx) {
		
		//ctx.setNextState(TableExtractorState.instance());
		a=a.replaceAll("(\\|+)", " ").replaceAll("[\\n\\r]+", "\\|\n").replaceAll(" $", "\\|").replaceAll("( +)", " ").replaceAll(" \\|", "\\|").trim();
		
		//ctx.update();
	}
}