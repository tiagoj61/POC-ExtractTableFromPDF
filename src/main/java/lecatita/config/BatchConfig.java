package lecatita.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lecatita.dao.interfc.ILineDao;
import lecatita.enumeration.IdenfierStepEnum;
import lecatita.listener.JobCompletionListener;
import lecatita.service.ILineService;
import lecatita.service.ISendService;
import lecatita.step.processor.ProcessorDownload;
import lecatita.step.processor.line.ProcessorLine;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.reader.ReaderDownload;
import lecatita.step.reader.ReaderLine;
import lecatita.step.reader.ReaderTable;
import lecatita.step.tasklet.TasklerSend;
import lecatita.step.writer.WriterDownload;
import lecatita.step.writer.WriterLine;
import lecatita.step.writer.WriterTable;

@Configuration
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
	@Autowired
	private ILineService iLineService;
	@Autowired
	private ISendService iSendService;
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.start(downloadStep()).next(tableStep()).next(lineStep()).build();
	}

	@Bean
	public Step downloadStep() {

		return stepBuilderFactory.get("downloadStep").<String, String>chunk(1).reader(new ReaderDownload())
				.processor(new ProcessorDownload()).writer(new WriterDownload()).listener(promotionListener()).build();
	}

	@Bean
	public Step tableStep() {
		return stepBuilderFactory.get("tableStep").<String, String>chunk(1).reader(new ReaderTable())
				.writer(new WriterTable()).listener(promotionListener()).build();
	}

	@Bean
	public Step lineStep() {
		return stepBuilderFactory.get("lineStep").<String, LineContext>chunk(1).reader(new ReaderLine())
				.processor(new ProcessorLine()).writer(new WriterLine(iLineService)).listener(promotionListener())
				.build();
	}

	@Bean
	public Step sendStep() {
		return stepBuilderFactory.get("sendStep").tasklet(sendTasklet()).build();
	}

	@Bean
	public TasklerSend sendTasklet() {
		return new TasklerSend(iSendService);
	}

	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(null);
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

	@Bean
	public ExecutionContextPromotionListener promotionListener() {
		ExecutionContextPromotionListener listener = new ExecutionContextPromotionListener();
		listener.setKeys(new String[] { IdenfierStepEnum.TABLE_KEY.getValue(), IdenfierStepEnum.LINE_KEY.getValue() });
		return listener;
	}

}