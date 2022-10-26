package lecatita.step.processor.table;

import java.util.UUID;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemProcessor;

import lecatita.enumeration.PathEnum;
import lecatita.step.processor.table.statemachine.context.ContextTable;

public class ProcessorTable implements ItemProcessor<String, String> {
	private String fileToRead;

	@BeforeStep
	public void retrieveInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		fileToRead = String.valueOf(jobContext.get(PathEnum.FILE_KEY.getValue()));
	}

	@Override
	public String process(String data) throws Exception {
		return null;
	}

}