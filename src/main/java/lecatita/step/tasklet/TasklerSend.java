package lecatita.step.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import lecatita.service.ISendService;

public class TasklerSend implements Tasklet {
	private ISendService iSendService;

	public TasklerSend(ISendService iSendService) {
		this.iSendService = iSendService;
	}

	@Override	
	public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {
		iSendService.sendLineResult();

		return RepeatStatus.FINISHED;
	}
}