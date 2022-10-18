package lecatita;
import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.pdfbox.pdmodel.PDDocument;

import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;


public class Poc {

	
	public static void main(String[] args) throws IOException {
		final String FILENAME = "D:\\Users\\Pc\\Documents\\POCs\\XP.pdf";

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

	}
//	SpreadsheetExtractionAlgorithm se = new SpreadsheetExtractionAlgorithm();
//    List<? extends Rectangle> cells = Arrays.asList(CELLS);
//    List<Rectangle> expected = Arrays.asList(EXPECTED_RECTANGLES);
//    Collections.sort(expected);
//    List<Rectangle> foundRectangles = se.findSpreadsheetsFromCells(cells);
	
	
//    Page page = UtilsForTesting
//            .getPage("src/test/resources/technology/tabula/spanning_cells.pdf", 1);
//    String expectedJson = UtilsForTesting.loadJson("src/test/resources/technology/tabula/json/spanning_cells.json");
//    SpreadsheetExtractionAlgorithm se = new SpreadsheetExtractionAlgorithm();
//    List<? extends Table> tables = se.extract(page);
//    replacedertEquals(2, tables.size());
//             

//
//	@Override
//	public List<Table> extract(Page page) {
//		List<TextElement> textElements = page.getText();
//		if (textElements.size() == 0) {
//			return Arrays.asList(new Table[] { Table.EMPTY });
//		}
//		List<TextChunk> textChunks = this.verticalRulings == null ? TextElement.mergeWords(page.getText())
//				: TextElement.mergeWords(page.getText(), this.verticalRulings);
//		List<Line> lines = TextChunk.groupByLines(textChunks);
//		List<Float> columns = null;
//		if (this.verticalRulings != null) {
//			Collections.sort(this.verticalRulings, new Comparator<Ruling>() {
//
//				@Override
//				public int compare(Ruling arg0, Ruling arg1) {
//					return Double.compare(arg0.getLeft(), arg1.getLeft());
//				}
//			});
//			columns = new ArrayList<Float>(this.verticalRulings.size());
//			for (Ruling vr : this.verticalRulings) {
//				columns.add(vr.getLeft());
//			}
//		} else {
//			columns = columnPositions(lines);
//		}
//		Table table = new Table(page, this);
//		for (int i = 0; i < lines.size(); i++) {
//			Line line = lines.get(i);
//			List<TextChunk> elements = line.getTextElements();
//			Collections.sort(elements, new Comparator<TextChunk>() {
//
//				@Override
//				public int compare(TextChunk o1, TextChunk o2) {
//					return new java.lang.Float(o1.getLeft()).compareTo(o2.getLeft());
//				}
//			});
//			for (TextChunk tc : elements) {
//				if (tc.isSameChar(Line.WHITE_SPACE_CHARS)) {
//					continue;
//				}
//				int j = 0;
//				boolean found = false;
//				for (; j < columns.size(); j++) {
//					if (tc.getLeft() <= columns.get(j)) {
//						found = true;
//						break;
//					}
//				}
//				table.add(tc, i, found ? j : columns.size());
//			}
//		}
//		return Arrays.asList(new Table[] { table });
//	}
}
