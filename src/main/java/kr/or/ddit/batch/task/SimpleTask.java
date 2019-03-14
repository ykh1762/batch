package kr.or.ddit.batch.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

public class SimpleTask {
	// dfdf 2019 0314 1205
	private Logger logger = LoggerFactory.getLogger(SimpleTask.class);

	@Scheduled(cron="*/3 * * * * *")
	public void simpleTask(){
		logger.debug("simpleTask");
	}
	
}
