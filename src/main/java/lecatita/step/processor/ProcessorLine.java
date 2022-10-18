package lecatita.step.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.statemachine.context.Context;

public class ProcessorLine implements ItemProcessor<String, Context> {

	@Override
	public Context process(String data) throws Exception {
		System.out.println("Processor Line\n" + data + "\n----------------");
		Context ctx = new Context(UUID.randomUUID().toString(), data);
		ctx.update();

		return ctx;
	}

}