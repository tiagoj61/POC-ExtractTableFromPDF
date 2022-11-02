package lecatita.config;

import java.io.File;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import lecatita.listener.JobCompletionListener;
import lecatita.step.processor.ProcessorDownload;
import lecatita.step.processor.line.ProcessorLine;
import lecatita.step.processor.line.statemachine.context.LineContext;
import lecatita.step.processor.table.ProcessorTable;
import lecatita.step.reader.ReaderDownload;
import lecatita.step.reader.ReaderLine;
import lecatita.step.reader.ReaderTable;
import lecatita.step.writer.WriterDownload;
import lecatita.step.writer.WriterLine;
import lecatita.step.writer.WriterTable;

@Configuration
public class DataSourceConfig {

	@Bean
	public DataSource getDataSource() {
		var config = new HikariConfig();
		String pass = "";
		config.setDriverClassName("org.h2.Driver");
		config.setJdbcUrl("jdbc:h2:file:~/spring-boot-h2-db");
		config.setUsername("sa");
		config.setPassword(pass);
		return new HikariDataSource(config);
	}

}