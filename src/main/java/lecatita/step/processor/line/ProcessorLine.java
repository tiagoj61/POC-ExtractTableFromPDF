package lecatita.step.processor.line;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.line.statemachine.context.ContextLine;

public class ProcessorLine implements ItemProcessor<String, ContextLine> {

	@Override
	public ContextLine process(String data) throws Exception {
		ContextLine ctx = new ContextLine(UUID.randomUUID().toString(), data);
		ctx.update();

		return ctx;
	}

}