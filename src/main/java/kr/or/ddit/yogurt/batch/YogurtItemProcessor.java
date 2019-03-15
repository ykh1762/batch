package kr.or.ddit.yogurt.batch;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import kr.or.ddit.yogurt.model.CycleVo;
import kr.or.ddit.yogurt.model.DailyVo;

public class YogurtItemProcessor implements ItemProcessor<CycleVo, List<DailyVo>>{
	private Logger logger = LoggerFactory.getLogger(YogurtItemProcessor.class);
	// 2019 0314 1556

	// spel(spring el)을 통해 jobParameter를 주입받기 위해서는 해당 bean의 스코프가 'step'이어야 함.
	@Value("#{jobParameters[ym]}")
	private String ym;
	
	@Override
	public List<DailyVo> process(CycleVo cycleVo) throws Exception {
		logger.debug("process ym : {}", ym);
		// CycleVo를 받아서 List<DailyVo>로 가공 후 리턴.
		
//		String ym = "201903";
		
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
		
		return dailyVoList;
	}

	
	
	

}








