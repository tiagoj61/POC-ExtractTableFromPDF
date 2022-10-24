	package lecatita.step.processor;

import java.io.File;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.item.ItemProcessor;

public class ProcessorDownload implements ItemProcessor<String, File> {

	@Override
	public File process(String path) throws Exception {
		   // Loading PDF
        File pdffile
            = new File("src/main/resources/yourFile.pdf");
        PDDocument document = PDDocument.load(pdffile);
  
        // Splitter Class
        Splitter splitting = new Splitter();
  
        // Splitting the pages into multiple PDFs
        List<PDDocument> Page = splitting.split(document);
  
        // Using a iterator to Traverse all pages
        Iterator<PDDocument> iteration
            = Page.listIterator();
  
        // Saving each page as an individual document
            PDDocument pd = Page.get(32);
            pd.save("src/main/resources/cuted.pdf");
        System.out.println("Splitted Pdf Successfully.");
        document.close();
		return pdffile;
	}

}