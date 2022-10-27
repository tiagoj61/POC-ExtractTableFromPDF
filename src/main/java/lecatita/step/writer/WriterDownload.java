package lecatita.step.writer;

import java.util.List;

import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;

import lecatita.dao.interfc.ILineDao;
import lecatita.enumeration.IdenfierStepEnum;
import lecatita.enumeration.PathEnum;

public class WriterDownload implements ItemWriter<String> {
	private ILineDao dao;
	private StepExecution stepExecution;

	public WriterDownload(ILineDao dao) {
		this.dao = dao;
	}

	@BeforeStep
	public void saveStepExecution(StepExecution stepExecution) {
		this.stepExecution = stepExecution;
	}

	// TODO insert url do file no banco, pra isso precisa do S3
	@Override
	public void write(List<? extends String> files) throws Exception {
		// dao.insert();
		for (int i = 0; i < files.size(); i++) {
			ExecutionContext stepContext = this.stepExecution.getExecutionContext();
			stepContext.put(IdenfierStepEnum.TABLE_KEY.getValue(), files.get(i));
		}

	}

}