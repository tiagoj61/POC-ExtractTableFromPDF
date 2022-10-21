package lecatita.step.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

import lecatita.step.processor.table.statemachine.context.ContextTable;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;

public class ReaderTable implements ItemReader<String> {
	private String[] tabelasExtraidas;
	private String messages;

	private int count = 0;

	//TODO ESSE READER LE O ULTIMO ARQUIVO IMPUTADO NO BANCO
	public ReaderTable(String[] ori) {
		tabelasExtraidas=ori;
		
	}

	@BeforeRead
	private void before() throws IOException {
		messages = extractTable();
		String tabelaOriginal = messages;
		ContextTable ctx = new ContextTable(UUID.randomUUID().toString(), messages);
		ctx.update();
		//TODO isso tbm é o banco
		
		tabelasExtraidas[0]=ctx.getTabelasExtraidas()[0];
		tabelasExtraidas[1]=ctx.getTabelasExtraidas()[1];
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (count < tabelasExtraidas.length) {
			return tabelasExtraidas[count++];
		} else {
			count = 0;
		}
		return null;
	}

	public static String extractTable() throws IOException {
		final String FILENAME = "D:\\Users\\Pc\\Documents\\POCs\\XP.pdf";
		String retorno = "";
		PDDocument pd = PDDocument.load(new File(FILENAME));

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
						retorno = retorno + text+" " ;
					}
				}
				retorno=retorno+"|\n";

			}
		}
		return retorno;

	}
}
