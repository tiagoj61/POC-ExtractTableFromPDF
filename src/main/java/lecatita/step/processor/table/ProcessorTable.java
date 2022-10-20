package lecatita.step.processor.table;

import java.util.UUID;

import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.table.statemachine.context.ContextTable;

public class ProcessorTable implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		ContextTable ctx = new ContextTable(UUID.randomUUID().toString(), data);
		ctx.update();
		return data.toUpperCase();
	}

}