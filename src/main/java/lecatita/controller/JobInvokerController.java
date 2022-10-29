package lecatita.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JobInvokerController {

	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	Job processJob;

//http://localhost:8080/invokejob?pagina=107&downloadUrl=https://api.mziq.com/mzfilemanager/v2/d/d1820734-8b3f-4a23-8642-331a3a8561a6/8b28d1dc-6273-deb0-8371-5658e9c97c78?origin=1
	@RequestMapping(value = "/invokejob", method = RequestMethod.GET)
	public String handle(@RequestParam String pagina, @RequestParam String downloadUrl) throws Exception {
		JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis()).addString("downloadUrl", downloadUrl.replace(" ", "+"))
				.addString("pagina", pagina).toJobParameters();
		jobLauncher.run(processJob, jobParameters);

		return "Batch job has been invoked";
	}
}