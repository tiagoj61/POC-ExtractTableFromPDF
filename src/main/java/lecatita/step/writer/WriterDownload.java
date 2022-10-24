package lecatita.step.writer;

import java.io.File;
import java.util.List;

import org.springframework.batch.item.ItemWriter;

public class WriterDownload implements ItemWriter<File> {

	@Override
	public void write(List<? extends File> messages) throws Exception {
		for (File msg : messages) {
			System.out.println("Writing the data " + msg);
		}
	}

}