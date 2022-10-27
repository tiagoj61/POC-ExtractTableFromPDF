package lecatita.step.writer;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ItemWriter;

import lecatita.step.processor.line.statemachine.context.ContextLine;

public class WriterLine implements ItemWriter<ContextLine> {

	// TODO dados que ja estao trataos no banco
	@Override
	public void write(List<? extends ContextLine> lines) throws Exception {
		for (ContextLine msg : lines) {
			System.out.println("Writing the data " + msg);
			// Isso AQUI é pra saber em qual tabela inserir, homem mulher e tal
			// StringUtils.getLevenshteinDistance(null, null);
		}
	}

}