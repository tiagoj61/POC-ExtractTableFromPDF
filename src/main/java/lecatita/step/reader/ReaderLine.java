package lecatita.step.reader;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.util.FileUtils;

import lecatita.enumeration.PathEnum;

public class ReaderLine implements ItemReader<String>, StepExecutionListener {

	// Exemplo brdaesco
//	private String[] completeTable = { ""
//			+ "Gênero 2018 2019 2020 2021*|"
//			+ "Homens 88 88 90 82|"
//			+ "Mulheres 12 12 10 18|"};
//Exemplo itau - algoritmo de tabela dela vai ser punk
//	private String[] completeTable = { ""
//			+ "2021 2020 2019|"
//			+ "Índice de satisfação da pesquisa Pulso - mulheres 87% 87% 86%|"
//			+ "Índice de satisfação da pesquisa Pulso - homens 89% 89% 87%|"};
//Exemplo Pan mudou o delimitador tem que ver isso no algoritomo de tablea
//	private String[] completeTable = { ""
//			+ "Nível F M TOTAL F M TOTAL F M TOTAL||"
//			+ "Gerência 99 174 273 113 196 309 116 223 339||"
//			+ "Coord/Espec.  80 127 207 90 149 239 129 248 377||"
//			+ "Analistas 465 533 998 487 566 1.053 612 827 1.439||"
//			+ "Assistentes 491 489 980 480 416 896 560 425 985||"
//			+ "Total 1.135 1.323 2.458 1.170 1.327 2.497 1.417 1.723 3.140||"};
//Exemplo Pan mudou o delimitador tem que ver isso no algoritomo de tablea
	private String[] completeTable;

	private int count;

	@BeforeStep
	public void retrieveInterstepData(StepExecution stepExecution) {
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		completeTable = (String[]) jobContext.get("a");
	}

	public ReaderLine(String[] ori) {
		completeTable = ori;
	}

	@Override
	public void beforeStep(StepExecution stepExecution) {
		count = 0;
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		try {
			return completeTable[count++];
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
