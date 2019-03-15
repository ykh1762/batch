package kr.or.ddit.yogurt.dao;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import kr.or.ddit.yogurt.dao.IYogurtDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:kr/or/ddit/config/spring/context-batch.xml")
public class YogurtDaoTest {

	@Resource(name="yogurtDao")
	private IYogurtDao yogurtDao;
	
	/**
	 * 
	 * Method : testDailyBatchYm
	 * 작성자 : PC19
	 * 변경이력 :
	 * Method 설명 : 일실적 생성 테스트
	 */
	@Test
	public void testDailyBatchYm() {
		/***Given***/
		String ym = "201904";
		yogurtDao.deleteDailyAllYm(ym);
		
		/***When***/
		int insertCnt = yogurtDao.dailyBatchYm(ym);

		/***Then***/
		assertTrue(insertCnt > 50);
	}

}
