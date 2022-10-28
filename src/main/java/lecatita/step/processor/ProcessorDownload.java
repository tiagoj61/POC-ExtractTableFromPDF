package lecatita.step.processor;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.List;

import org.apache.pdfbox.multipdf.Splitter;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemProcessor;

import lecatita.enumeration.PathEnum;

public class ProcessorDownload implements ItemProcessor<String, String> {
	private String logInfo;
	private int pageToExtract;

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) throws IOException {
		JobParameters jobParameters = stepExecution.getJobParameters();
		pageToExtract = Integer.valueOf(jobParameters.getString("pagina"));
	}

	@Override
	public String process(String fileName) throws Exception {
		String filePathRetun = extractPageFromFile(fileName);
		return filePathRetun;
	}

	private String extractPageFromFile(String fileName) throws IOException {
		logInfo = "Starting cut " + fileName;
		System.out.println(logInfo);

		String filePath = PathEnum.FILE_STORE.getValue() + fileName + ".pdf";
		File pdffile = new File(filePath);

		PDDocument document = PDDocument.load(pdffile);

		Splitter splitting = new Splitter();

		List<PDDocument> Page = splitting.split(document);

		String fileCutPath = PathEnum.FILE_CUT_STORE.getValue() + fileName + ".pdf";

		PDDocument pd = Page.get(pageToExtract);
		pd.save(fileCutPath);

		document.close();

		logInfo = "Finished cut, at " + fileCutPath;
		System.out.println(logInfo);
		return fileName;

	}

}