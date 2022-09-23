package lecatita.step.reader;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.util.FileUtils;

public class ReaderLine implements ItemReader<String>, StepExecutionListener {

	private String[] completeTable = { "testeone 1 2 3 4 || testetwo 10 20 30 40 || testethree 100 200 300 400 ||" };

	private int count;

	@Override
	public void beforeStep(StepExecution stepExecution) {
		count = 0;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			count++;
			return completeTable[count - 1];
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Out bounds");
			return null;
		}
	}

	@Override
	public ExitStatus afterStep(StepExecution stepExecution) {
		return ExitStatus.COMPLETED;
	}

}
