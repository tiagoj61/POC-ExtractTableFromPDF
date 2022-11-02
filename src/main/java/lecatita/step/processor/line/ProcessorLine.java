package lecatita.step.processor.line;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.line.statemachine.context.LineContext;

public class ProcessorLine implements ItemProcessor<String, LineContext> {

	@Override
	public LineContext process(String data) throws Exception {
		LineContext ctx = new LineContext(UUID.randomUUID().toString(), data);
		ctx.update();

		return ctx;
	}

}