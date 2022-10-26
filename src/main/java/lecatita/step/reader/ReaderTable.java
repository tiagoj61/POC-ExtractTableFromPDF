package lecatita.step.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lecatita.enumeration.PathEnum;
import lecatita.step.processor.table.statemachine.context.ContextTable;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;

public class ReaderTable implements ItemReader<String> {
	private String[] tabelasExtraidas;
	private String textReaded;
	private String fileToRead;
	private StepExecution stepExecution;
	private int count = 0;

	// TODO ESSE READER LE O ULTIMO ARQUIVO IMPUTADO NO BANCO
	public ReaderTable(String[] ori) {
		tabelasExtraidas = ori;

	}

	@BeforeStep
	public void retrieveInterstepData(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
		JobExecution jobExecution = stepExecution.getJobExecution();
		ExecutionContext jobContext = jobExecution.getExecutionContext();
		fileToRead = String.valueOf(jobContext.get(PathEnum.FILE_KEY.getValue()));
	}
	@BeforeRead
	private void before() throws IOException {
		textReaded = extractTable();
		ContextTable ctx = new ContextTable(fileToRead, textReaded);
		ctx.update();

		tabelasExtraidas = ctx.getTabelasExtraidas();
		ExecutionContext stepContext = this.stepExecution.getExecutionContext();
		stepContext.put("a", tabelasExtraidas);
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (count < 1) {
			count++;
			return textReaded;
		} else {
			count = 0;
		}
		return null;
	}

	public String extractTable() throws IOException {
		String retorno = "";
		String filePathToRead = PathEnum.FILE_CUT_STORE.getValue() + fileToRead + ".pdf";
		PDDocument pd = PDDocument.load(new File(filePathToRead));

		int totalPages = pd.getNumberOfPages();
		System.out.println("Totent: " + totalPages);

		ObjectExtractor oe = new ObjectExtractor(pd);
		BasicExtractionAlgorithm sea = new BasicExtractionAlgorithm();
		Page page = oe.extract(1);

		// extract text from the table after detecting
		List<Table> table = (List<Table>) sea.extract(page);
		for (Table tables : table) {
			List<List<RectangularTextContainer>> rows = tables.getRows();

			for (int i = 0; i < rows.size(); i++) {

				List<RectangularTextContainer> cells = rows.get(i);
				for (int j = 0; j < cells.size(); j++) {

					String text = cells.get(j).getText();
					if (!text.isEmpty()) {
						retorno = retorno + text + " ";
					}
				}
				retorno = retorno + "|\n";

			}
		}
		return retorno;

	}
}
