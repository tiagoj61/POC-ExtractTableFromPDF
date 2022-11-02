package lecatita.step.writer;

import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemWriter;

import lecatita.automaton.Context;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class WriterLine implements ItemWriter<LineContext> {

	// TODO dados que ja estao trataos no banco
	@Override
	public void write(List<? extends LineContext> lines) throws Exception {
		for (LineContext msg : lines) {
			System.out.println("Writing the data " + msg);
			Context context = new EquityContext(UUID.randomUUID().toString(), msg);
			context.update();
			// Isso AQUI é pra saber em qual tabela inserir, homem mulher e tal
			// StringUtils.getLevenshteinDistance(null, null);
			// ver se o header ou o indice é mais parecido com o cargo
		}
	}

}