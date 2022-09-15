	package lecatita.step.processor;

import org.springframework.batch.item.ItemProcessor;

public class ProcessorDownload implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		return data.toUpperCase();
	}

}