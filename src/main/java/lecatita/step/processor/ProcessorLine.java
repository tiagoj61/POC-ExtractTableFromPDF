	package lecatita.step.processor;

import org.springframework.batch.item.ItemProcessor;

public class ProcessorLine implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}