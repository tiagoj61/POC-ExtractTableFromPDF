package lecatita.step.reader;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lecatita.enumeration.IdenfierStepEnum;

public class ReaderLine implements ItemReader<String> {

	private String[] bruteTables;
	private int count;

	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		bruteTables = (String[]) jobContext.get(IdenfierStepEnum.LINE_KEY.getValue());

		count = 0;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return bruteTables[count++];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Out bounds");
			return null;
		}
	}

}
