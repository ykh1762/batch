package kr.or.ddit.yogurt.batch;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class DateTest {
	private Logger logger = LoggerFactory.getLogger(DateTest.class);

	@Test
	public void testLastDay() throws ParseException {
		/***Given***/
		String ym = "201903";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyymmdd");
		
		/***When***/
		Date stDate = sdf.parse(ym + "01"); // 시작일자
		
		Calendar cal = Calendar.getInstance();
		cal.setTime(stDate);
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date edDate = sdf.parse(ym + lastDay);

		/***Then***/
		assertEquals("20190301", sdf.format(stDate));
		assertEquals("20190331", sdf.format(edDate));
	}
	
	@Test
	public void testDtWhile() throws ParseException{
		// CycleVo를 받아서 List<DailyVo>로 가공 후 리턴.
		
		String ym = "201903";
		CycleVo cycleVo = new CycleVo();
		cycleVo.setCid(1);
		cycleVo.setPid(100);
		cycleVo.setDay(2);
		cycleVo.setCnt(2);
		
		// 해당년월의 시작일자 : 1일
		// 해당년월의 종료일자 : 28, 29, 30, 31일
		// 해당년월의 종료일자 구하기? -> test code
		
		// ex) 1번 고객이 100 제품을 2 요일에 3개를 먹는다.
		// 1 100 2 3
		
		// 1 100 20190304 3
		// 1 100 20190311 3
		// 1 100 20190318 3
		// 1 100 20190325 3
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date stDate = sdf.parse(ym + "01");
		Calendar cal = Calendar.getInstance();
		cal.setTime(stDate);
		
		int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		Date edDate = sdf.parse(ym + lastDay);
		
		// 시작일자, 종료일자 구함.
		
		List<DailyVo> dailyVoList = new ArrayList<>();
		
		// 시작일자 ~ 종료일자 loop.
		while(cal.getTimeInMillis() <= edDate.getTime()){
			int dayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
			
			// 애음주기 요일과 cal 날짜요일이 같으면 일실적으로 생성할 대상일자.
			if(dayOfWeek == cycleVo.getDay()){
				DailyVo dailyVo = new DailyVo();
				dailyVo.setCid(cycleVo.getCid());
				dailyVo.setPid(cycleVo.getPid());
				dailyVo.setDt(sdf.format(cal.getTime()));
				dailyVo.setCnt(cycleVo.getCnt());
				dailyVoList.add(dailyVo);
			}
			
			cal.add(Calendar.DAY_OF_MONTH, 1);
		}		
		
		logger.debug("dailyVoList : {}", dailyVoList);
	}
	

}






