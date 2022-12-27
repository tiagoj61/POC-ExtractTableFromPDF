package lecatita.controller;

import java.net.http.HttpResponse;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lecatita.dto.response.MensageResponseDTO;
import lecatita.thread.JobThread;

@RestController
public class JobInvokerController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

	@RequestMapping(value = "/invokejob", method = RequestMethod.GET)
	public ResponseEntity<MensageResponseDTO> handle(@RequestParam String pagina, @RequestParam String downloadUrl)
			throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
				.addString("downloadUrl", downloadUrl.replace(" ", "+")).addString("pagina", pagina).toJobParameters();

		JobThread jobThread = new JobThread(jobLauncher, processJob, jobParameters);
		jobThread.start();

		return ResponseEntity.ok(new MensageResponseDTO("Job iniciado"));
	}
}