package lecatita.step.reader;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.springframework.batch.core.annotation.BeforeRead;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

public class ReaderDownload implements ItemReader<String> {

	private String[] messages = { "javainuse.com", "Welcome to Spring Batch Example",
			"We use H2 Database for this example" };

	private int count = 0;

	@BeforeRead
	private void download() throws IOException {
		System.out.println("opening connection");
		URL url = new URL(
				"https://www.bradescori.com.br/wp-content/uploads/sites/541/2022/06/Relatorio-Integrado-2021.pdf");
		InputStream in = url.openStream();
		FileOutputStream fos = new FileOutputStream(new File("src/main/resources/yourFile.pdf"));

		System.out.println("reading from resource and writing to file...");
		int length = -1;
		byte[] buffer = new byte[1024];// buffer for portion of data from connection
		while ((length = in.read(buffer)) > -1) {
			fos.write(buffer, 0, length);
		}
		fos.close();
		in.close();

		System.out.println("File downloaded");

	}

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		if (count < messages.length) {
			return messages[count++];
		} else {
			count = 0;
		}
		System.out.println(count);
		return null;
	}

}
