package kr.or.ddit.batch.hello;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)

// {"", ""} -> src/main/resource, src/test/resources
@ContextConfiguration({"classpath:kr/or/ddit/config/spring/context-batch.xml", 
	"classpath:kr/or/ddit/config/spring/context-hello-batch-test.xml"})
public class HelloJobTest {

	@Autowired
	private JobLauncherTestUtils jobLauncher;
	
	@Test
	public void testHelloJob() throws Exception {
		/***Given***/
		
		/***When***/
		JobExecution jobExecution = jobLauncher.launchJob();

		/***Then***/
		assertEquals(ExitStatus.COMPLETED, jobExecution.getExitStatus());
	}

}
