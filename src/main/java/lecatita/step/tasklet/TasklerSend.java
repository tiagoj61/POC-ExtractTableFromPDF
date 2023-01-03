package lecatita.step.tasklet;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lecatita.enumeration.PathEnum;
import lecatita.service.ISendService;

public class TasklerSend implements Tasklet {
	private ISendService iSendService;
	private String empresaId;
	private String ano;

	public TasklerSend(ISendService iSendService) {
		this.iSendService = iSendService;
	}

	@Override
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
		JobParameters jobParameters = chunkContext.getStepContext().getStepExecution().getJobParameters();
		empresaId = (jobParameters.getString("empresaId"));
		ano = (jobParameters.getString("ano"));

		iSendService.sendLineResult(empresaId, ano);

		return RepeatStatus.FINISHED;
	}
}