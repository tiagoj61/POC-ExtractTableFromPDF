package lecatita.step.reader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;

import lecatita.enumeration.PathEnum;

@Scope(value = "step")
public class ReaderDownload implements ItemReader<String> {
	private String urlToDownload;
	private String uniqueFileId;
	private String logInfo;
	private int count = 0;

	public ReaderDownload() {
		this.uniqueFileId = UUID.randomUUID().toString().replace("-", "");
	}

	@BeforeStep
	public void beforeStep(final StepExecution stepExecution) throws IOException {
		JobParameters jobParameters = stepExecution.getJobParameters();
		urlToDownload = (jobParameters.getString("downloadUrl"));

		Files.createDirectories(Paths.get(PathEnum.FILE_STORE.getValue()));
		Files.createDirectories(Paths.get(PathEnum.FILE_CUT_STORE.getValue()));
	}

	@BeforeRead
	private void download() throws IOException {
		if (count == 0) {
			logInfo = "Starting download of " + urlToDownload;
			System.out.println(logInfo);

			URL url = new URL(urlToDownload);
			InputStream in = url.openStream();

			String filePath = PathEnum.FILE_STORE.getValue() + uniqueFileId + ".pdf";
			FileOutputStream fos = new FileOutputStream(new File(filePath));

			int length = -1;
			byte[] buffer = new byte[1024];
			while ((length = in.read(buffer)) > -1) {
				fos.write(buffer, 0, length);
			}
			fos.close();
			in.close();

			logInfo = "Finished download of " + urlToDownload;
			System.out.println(logInfo);
		}
	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		if (count < 1) {
			count++;
			return uniqueFileId;
		} else {
			count = 0;
		}
		return null;
	}

}
