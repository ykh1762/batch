package kr.or.ddit.yogurt.batch;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.scheduling.annotation.Scheduled;

public class YogurtTask {
	
	@Resource(name="jobLauncher")
	private JobLauncher jobLauncher;
	
	@Resource(name="yogurtJob")
	private Job yogurtJob;
	
	// 매달 1일 새벽 1시에 일실적 생성 배치잡 실행.
	// 초 분 시간 일 월 요일.
	@Scheduled(cron="* * 1 1 * *")
	public void yogurtDailyJob(){
		Map<String, JobParameter> map = new HashMap<>();
		
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		String ym = sdf.format(today);
		
		map.put("ym", new JobParameter(ym));
		
		//yogurtService.deleteDaily(ym);
		// -> DELETE DAILY WHERE DT LIKE '201904' || '%';
		
		try {
			jobLauncher.run(yogurtJob, new JobParameters(map));
		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException e) {
			e.printStackTrace();
		}
	}
	
	
	
}










