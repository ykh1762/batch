package kr.or.ddit.yogurt.service;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.batch.service.IBatchService;
import kr.or.ddit.yogurt.dao.IYogurtDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/config/spring/context-batch.xml")
public class BatchServiceTest {
	
	@Resource(name="batchService")
	private IBatchService batchService;

	@Resource(name="yogurtDao")
	private IYogurtDao yogurtDao;
	
	@Test
	public void testDailyBatchYm() {		
		/***Given***/
		String ym = "201904";
		yogurtDao.deleteDailyAllYm(ym);
		
		/***When***/
		int insertCnt = batchService.dailyBatchYm(ym);

		/***Then***/
		assertEquals(66, insertCnt);
	}
	
	
	

}




