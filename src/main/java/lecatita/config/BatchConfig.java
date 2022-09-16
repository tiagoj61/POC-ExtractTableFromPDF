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
import org.springframework.batch.core.step.tasklet.SystemCommandTasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lecatita.listener.JobCompletionListener;
import lecatita.step.processor.ProcessorDownload;
import lecatita.step.processor.ProcessorTable;
import lecatita.step.reader.ReaderDownload;
import lecatita.step.reader.ReaderTable;
import lecatita.step.writer.WriterDownload;
import lecatita.step.writer.WriterTable;

@Configuration
@EnableAutoConfiguration(exclude = { HibernateJpaAutoConfiguration.class })
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Bean
	public Job processJob() {
		return jobBuilderFactory.get("processJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(downloadStep())
				.on("FAILED")
			    .to(errorStep())
			    .from(downloadStep())
			    .on("*")
			    .to(tableStep())
			    .end()
				.build();
	}

	@Bean
	public Step downloadStep() {
		return stepBuilderFactory.get("downloadStep").<String, String>chunk(1).reader(new ReaderDownload())
				.processor(new ProcessorDownload()).writer(new WriterDownload()).build();
	}

	@Bean
	public Step tableStep() {
		return stepBuilderFactory.get("tableStep").<String, String>chunk(1).reader(new ReaderTable())
				.processor(new ProcessorTable()).writer(new WriterTable()).build();
	}

	@Bean
	public Step sucessStep() {
		return stepBuilderFactory.get("sucessStep").<String, String>chunk(1).reader(new ReaderDownload())
				.processor(new ProcessorDownload()).writer(new WriterDownload()).build();
	}
	@Bean
	public Step errorStep() {
	  return this.stepBuilderFactory
	    .get("errorStep")
	    .tasklet(systemCommandTasklet())
	    .build();
	}
	@Bean
	public SystemCommandTasklet systemCommandTasklet() {
	System.out.println("Error");
	  return null;
	}
	

	@Override
	public void setDataSource(DataSource dataSource) {
		super.setDataSource(null);
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobCompletionListener();
	}

}