	package lecatita.step.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.statemachine.context.Context;

public class ProcessorLine implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		Context ctx = new Context(UUID.randomUUID().toString(), data);
		ctx.update();
		System.out.println("LINE");
		System.out.println(data);
		return data.toUpperCase();
	}

}