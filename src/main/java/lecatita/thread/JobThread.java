package lecatita.thread;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;

public class JobThread extends Thread {

	JobLauncher jobLauncher;
	Job processJob;
	JobParameters jobParameters;

	public String downloadUrl;
	public String pagina;

	public JobThread(JobLauncher jobLauncher, Job processJob, JobParameters jobParameters) {
		super();
		this.jobLauncher = jobLauncher;
		this.processJob = processJob;
		this.jobParameters = jobParameters;
	}

	public void run() {
		try {

			jobLauncher.run(processJob, jobParameters);
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
}