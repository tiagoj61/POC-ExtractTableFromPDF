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
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lecatita.dao.interfc.ILineDao;
import lecatita.enumeration.PathEnum;
import lecatita.listener.JobCompletionListener;
import lecatita.step.processor.ProcessorDownload;
import lecatita.step.processor.line.ProcessorLine;
import lecatita.step.processor.line.statemachine.context.ContextLine;
import lecatita.step.processor.table.ProcessorTable;
import lecatita.step.reader.ReaderDownload;
import lecatita.step.reader.ReaderLine;
import lecatita.step.reader.ReaderTable;
import lecatita.step.writer.WriterDownload;
import lecatita.step.writer.WriterLine;
import lecatita.step.writer.WriterTable;

@Configuration
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {
	@Autowired
	private ILineDao lineDao;
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	String downloadUrl = "https://ri.bancopan.com.br/ShowCanal/Download.aspx?Arquivo=t9eMM7vxgKQB+bwoni+Rrw==";
	int pagina =48;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;
	// TODO: isso deveria ser o banco dde daods
	private static String[] ori = new String[2];

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob").incrementer(new RunIdIncrementer()).listener(listener())
				.start(downloadStep()).next(tableStep()).next(lineStep()).build();
	}

	@Bean
	public Step downloadStep() {
		return stepBuilderFactory.get("downloadStep").<String, String>chunk(1).reader(new ReaderDownload(downloadUrl))
				.processor(new ProcessorDownload(pagina)).writer(new WriterDownload(lineDao))
				.listener(promotionListener()).build();
	}

	@Bean
	public Step tableStep() {
		return stepBuilderFactory.get("tableStep").<String, String>chunk(1).reader(new ReaderTable(ori))
				.writer(new WriterTable()).listener(promotionListener()).build();
	}

	@Bean
	public Step lineStep() {
		return stepBuilderFactory.get("lineStep").<String, ContextLine>chunk(1).reader(new ReaderLine(ori))
				.processor(new ProcessorLine()).writer(new WriterLine()).listener(promotionListener()).build();
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
		listener.setKeys(new String[] { PathEnum.FILE_KEY.getValue(), "a" });
		return listener;
	}

}