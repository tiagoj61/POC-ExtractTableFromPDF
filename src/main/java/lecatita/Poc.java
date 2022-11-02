package lecatita;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;
import java.util.UUID;
import java.util.regex.Pattern;

import org.apache.pdfbox.pdmodel.PDDocument;

import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.table.statemachine.context.ContextTable;
import lecatita.step.processor.table.statemachine.state.impl.TableExtractorState;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.BasicExtractionAlgorithm;


public class Poc {

	private static String a = "405-1 Diversidade em órgãos de governança e empregados|\n"
			+ "Composição do Conselho de Administração - Gênero 2020 2021|\r\n"
			+ "Masculinos 11 92% 11 92|\r\n"
			+ "Femininos 1 8% 1 8%|\r\n"
			+ "Faixa Etária Gênero|\r\n"
			+ "Cargos|\r\n"
			+ "-30 anos 30 a 50 anos +50 anos Homens Mulheres|\r\n"
			+ "Presidente/Diretor 0% 94% 6% 92% 8%|\r\n"
			+ "Superintendente/VP 4% 90% 6% 83% 17%|\r\n"
			+ "Gerente/Gerente Geral 6% 93% 1% 59% 41%|";

		public static void updateState() {

			List<String> linhas = new ArrayList<>(Arrays.asList(a.split("\\|")));
			HashMap<Integer, String> a = new HashMap<Integer, String>();
			//TODO Criar metodo, conta a quantidade de numeros agrupados na linha
			for(String s: linhas) {
				String[] arra=s.split(" ");
				int qtdNum=0;
				for(int i=0;i<arra.length;i++) {
					if(verifyContainsNumber(arra[i])){
						qtdNum++;
					}
				}
				a.put(qtdNum, s);
			}
			int posEntrada=0;
			int vcalant=0;
			boolean maioUm=false;
			for(Map.Entry<Integer, String> entry : a.entrySet()) {
				
				
				if(entry.getKey()>1) {
					if(entry.getKey()==vcalant) {
						posEntrada=posEntrada-2;
						posEntrada=generateTable(a,posEntrada,entry.getKey());
					}
					vcalant=entry.getKey();
					
				}
				posEntrada++;
			}
				
			//ctx.update();
		}
		private static int generateTable(HashMap<Integer, String> tudo,int posEntrada,int qtd) {
			int i=0;
			List<String> newTable= new ArrayList<String>();
			for(Map.Entry<Integer, String> entry : tudo.entrySet()) {
				if(i==posEntrada) {
					newTable.add(entry.getValue());
				}else if(i>posEntrada) {
					if(qtd==entry.getKey()) {
					newTable.add(entry.getValue());
					}else {
						break;
					}
				}
				i++;
			}
			
			return posEntrada;
		}
		//TODO TA DUPLICADO
		private static boolean verifyContainsNumber(String atual) {
			String regex = "(.)*(\\d)(.)*";
			Pattern pattern = Pattern.compile(regex);
			return pattern.matcher(atual).matches();
		}
	
	public static void main(String[] args) throws IOException {
		test();
//		String a = "Composição do Conselho de Administração - Gênero 2020 2021|\r\n"
//				+ "Masculinos 11 92% 11 92%| 4|\r\n"
//				+ "Femininos 1 8% 1 8% 4|\r\n"
//				+ "Composição do Conselho de Administração - Faixa Etária||2020||||2021|||\r\n"
//				+ "Abaixo de 30 anos|0||0%||0|0%|||\r\n"
//				+ "Entre 30 e 50 anos|9|75%|||10|83%|||\r\n"
//				+ "Acima de 50 anos|3|25%|||2|17%|||\r\n"
//				+ "Faixa Etária||||Gênero|||||\r\n"
//				+ "Cargos|||||||||\r\n"
//				+ "-30 anos 30 a 50 anos||+50 anos||Homens||Mulheres|||\r\n"
//				+ "Presidente/Diretor 0% 94%||6%||92%||8%|||\r\n"
//				+ "Superintendente/VP 4% 90%||6%||83%||17%|||\r\n"
//				+ "Gerente/Gerente Geral 6% 93%||1%||59%||41%|||\r\n"
//				+ "Coordenador/Consultor 22% 77%||1%||67%||33%|||\r\n"
//				+ "Técnico/Analista/Supervisor 44% 55%||1%||69%||31%|||\r\n"
//				+ "Operacional 64% 32%||4%||0%||0%|||\r\n"
//				+ "Estagiário 99% 1%||0%||72%||28%|||\r\n"
//				+ "Aprendiz 100% 0%||0%||60%||40%|||\r\n"
//				+ "Total Colaboradores 40% 58%||1%||66%||34%|||\r\n"
//				+ "||||||||108|";
//		System.out.println(a.replaceAll("(\\|+)", " "));
//		System.out.println("_____________________________________");
//		System.out.println();
//		System.out.println("_____________________________________");
//		 final String s = "5\n"
//		            + "9 6\n"
//		            + "4 6 8\n"
//		            + "0 7 1 5";
//		    final InputStream is = new ByteArrayInputStream(s.getBytes());
//		    final int[][] array = new int[4][];
//		    try (final BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
//		        String line;
//		        for (int i = 0; (line = br.readLine()) != null; ++i) {
//		            final String[] tokens = line.split("\\s");
//		            final int[] parsed = new int[tokens.length];
//		            for (int j = 0; j < tokens.length; ++j) {
//		                parsed[j] = Integer.parseInt(tokens[j]);
//		            }
//		            array[i] = parsed;
//		        }
//		    }
	}
	public static void test() throws IOException {
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
