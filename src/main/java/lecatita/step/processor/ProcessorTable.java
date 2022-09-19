package lecatita.step.processor;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.statemachine.context.Context;

public class ProcessorTable implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		
		return data.toUpperCase();
	}

}