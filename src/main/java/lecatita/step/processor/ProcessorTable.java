package lecatita.step.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.statemachine.context.DeliveryContext;

public class ProcessorTable implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		DeliveryContext ctx = new DeliveryContext(UUID.randomUUID().toString(), data);
		ctx.update();
		return data.toUpperCase();
	}

}