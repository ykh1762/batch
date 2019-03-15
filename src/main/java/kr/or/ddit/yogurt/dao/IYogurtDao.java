package kr.or.ddit.yogurt.dao;

public interface IYogurtDao {

	// 일실적 일괄 삭제.
	int deleteDailyAllYm(String ym);
	
	// 일실적 입력.
	int dailyBatchYm(String ym);
	
	
}








