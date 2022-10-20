package lecatita.step.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;

import lecatita.step.processor.line.statemachine.context.ContextLine;

public class WriterLine implements ItemWriter<ContextLine> {

	@Override
	public void write(List<? extends ContextLine> lines) throws Exception {
		for (ContextLine msg : lines) {
			System.out.println("Writing the data " + msg);
		}
	}

}