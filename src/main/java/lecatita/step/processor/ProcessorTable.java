package lecatita.step.processor;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.item.ItemProcessor;

import lecatita.step.processor.statemachine.context.Context;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;

public class ProcessorTable implements ItemProcessor<String, String> {

	@Override
	public String process(String data) throws Exception {
		final String FILENAME = "D:\\Users\\Pc\\Documents\\POCs\\Pan.pdf";

		PDDocument pd = PDDocument.load(new File(FILENAME));

		int totalPages = pd.getNumberOfPages();
		System.out.println("Totent: " + totalPages);

		ObjectExtractor oe = new ObjectExtractor(pd);
		BasicExtractionAlgorithm sea = new BasicExtractionAlgorithm();
		Page page = oe.extract(1);

		System.out.println("----------------------------------------------------");

		// extract text from the table after detecting
		List<Table> table = (List<Table>) sea.extract(page);
		for (Table tables : table) {
			List<List<RectangularTextContainer>> rows = tables.getRows();

			for (int i = 0; i < rows.size(); i++) {

				List<RectangularTextContainer> cells = rows.get(i);

				for (int j = 0; j < cells.size(); j++) {
					System.out.print(cells.get(j).getText() + "|");
				}

				System.out.println();
				// System.out.println();
			}
			System.out.println("******************************************");
		}

		return data.toUpperCase();
	}

}