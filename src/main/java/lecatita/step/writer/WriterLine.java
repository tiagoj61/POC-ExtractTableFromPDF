package lecatita.step.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lecatita.step.processor.statemachine.context.Context;

public class WriterLine implements ItemWriter<Context> {

	@Override
	public void write(List<? extends Context> lines) throws Exception {
		for (Context msg : lines) {
			System.out.println("Writing the data " + msg);
		}
	}

}