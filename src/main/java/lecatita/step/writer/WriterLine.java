package lecatita.step.writer;

import java.util.List;
import java.util.UUID;

import org.springframework.batch.item.ItemWriter;

import lecatita.service.ILineService;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.writer.state.EquityContext;

public class WriterLine implements ItemWriter<LineContext> {
	private ILineService iLineService;

	public WriterLine(ILineService iLineService) {
		this.iLineService = iLineService;
	}

	// TODO dados que ja estao trataos no banco
	@Override
	public void write(List<? extends LineContext> lines) throws Exception {
		for (LineContext msg : lines) {
			System.out.println("Writing the data " + msg);
			EquityContext context = new EquityContext(UUID.randomUUID().toString(), msg);
			context.update();

			iLineService.insertBurdens(context.getBurdensData());
			// Isso AQUI é pra saber em qual tabela inserir, homem mulher e tal
			// StringUtils.getLevenshteinDistance(null, null);
			// ver se o header ou o indice é mais parecido com o cargo
		}
	}

}